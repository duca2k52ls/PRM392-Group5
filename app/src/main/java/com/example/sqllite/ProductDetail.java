package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqllite.DAO.ProductDAO;
import com.example.sqllite.Models.Products;
import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {
    private TextView txt_ID, txt_Name, txt_SupID, txt_CateID, txt_Quantity, txt_Price;
    private ImageView imageView;
    private AppDatabase db;
    private ProductDAO productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        txt_ID = findViewById(R.id.txt_ID);
        txt_Name = findViewById(R.id.txt_name);
        txt_SupID = findViewById(R.id.txt_supid);
        txt_CateID = findViewById(R.id.txt_cateid);
        txt_Quantity = findViewById(R.id.txt_quantity);
        txt_Price = findViewById(R.id.txt_price);
        imageView = findViewById(R.id.img_pro);

        int productId = getIntent().getIntExtra("product_id", 0);
        txt_ID.setText(String.valueOf(productId));

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        productDao = db.productDAO();

        new RetrieveProductTask().execute(productId);
    }

    private class RetrieveProductTask extends AsyncTask<Integer, Void, Products> {
        @Override
        protected Products doInBackground(Integer... productIds) {
            int productId = productIds[0];
            return productDao.findByOnlyID(productId);
        }

        @Override
        protected void onPostExecute(Products product) {
            super.onPostExecute(product);
            if (product != null) {
                txt_Name.setText(product.getProductName());
                txt_SupID.setText(String.valueOf(product.getSupplierID()));
                txt_CateID.setText(String.valueOf(product.getCategoryID()));
                txt_Quantity.setText(String.valueOf(product.getQuantityPerUnit()));
                txt_Price.setText(String.valueOf(product.getUnitPrice()));
                Picasso.get().load(product.getProductImage()).resize(600, 400).into(imageView);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}