package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sqllite.DAO.ProductDAO;
import com.example.sqllite.Models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adminHome extends AppCompatActivity {
    LinearLayout imageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        imageLayout = findViewById(R.id.imageLayout);
        List<String> imageUrlList = new ArrayList<>();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name-v2").build();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ProductDAO productDao = db.productDAO();
                List<Products> products = productDao.getAll();
                imageUrlList.clear(); // Xóa các phần tử cũ trong danh sách
                for (Products product : products) {
                    // Lấy giá trị string từ cột string của products
                    String imageUrl = product.getProductImage();
                    // Thêm vào danh sách imageUrlList
                    imageUrlList.add(imageUrl);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageLayout.removeAllViews(); // Xóa các ImageView cũ trong layout
                        for (int i = 0; i < imageUrlList.size(); i++) {
                            String imageUrl = imageUrlList.get(i);
                            int productId = products.get(i).getProductID();
                            ImageView imageView = new ImageView(adminHome.this);
                            Picasso.get().load(imageUrl).resize(500, 200).into(imageView);
                            imageLayout.addView(imageView);

                            imageView.setTag(productId); // Gắn ID sản phẩm vào ImageView
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int productId = (int) v.getTag(); // Lấy ID sản phẩm từ ImageView
                                    // Chuyển sang activity mới và truyền ID sản phẩm
                                    Intent intent = new Intent(adminHome.this, ProductDetail.class);
                                    intent.putExtra("product_id", productId);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
            }
        });
        t.start();

        ((Button)findViewById(R.id.btn_management)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(adminHome.this, AdminScreenActivity.class);
                        startActivity(intent);
                    }
                });
                t.start();
            }
        });
    }
}