package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqllite.DAO.ProductDAO;
import com.example.sqllite.Models.Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    EditText edt_ProductId, edt_Productname, edt_SupplierId, edt_CategoryId, edt_QuantityPerUnit, edt_UnitPrice, edt_ProductImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        edt_ProductId = findViewById(R.id.text_id);
        edt_Productname = findViewById(R.id.text_name);
        edt_SupplierId = findViewById(R.id.text_supid);
        edt_CategoryId = findViewById(R.id.text_cateid);
        edt_QuantityPerUnit = findViewById(R.id.text_quantity);
        edt_UnitPrice = findViewById(R.id.text_price);
        edt_ProductImage = findViewById(R.id.text_image);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        ((Button)findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProductDAO productDao = db.productDAO();
                        productDao.insertAllProducts(new Products(Integer.parseInt(edt_ProductId.getText().toString()),
                                edt_Productname.getText().toString(), Integer.parseInt(edt_SupplierId.getText().toString()),
                                Integer.parseInt(edt_CategoryId.getText().toString()),
                                Integer.parseInt(edt_QuantityPerUnit.getText().toString()),
                                Double.parseDouble(edt_UnitPrice.getText().toString()),
                                edt_ProductImage.getText().toString()));
                    }
                });
                t.start();
            }
        });

        ((Button)findViewById(R.id.btn_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProductDAO productDao = db.productDAO();
                        int productIdToDelete = Integer.parseInt(edt_ProductId.getText().toString());
                        Products pro = productDao.getByIds(productIdToDelete);
                        productDao.deleteProduct(pro);
                    }
                });
                t.start();
            }
        });

        ((Button)findViewById(R.id.btn_edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProductDAO productDao = db.productDAO();
                        int productIdToUpdate = Integer.parseInt(edt_ProductId.getText().toString());
                        Products pro = productDao.getByIds(productIdToUpdate);
                        productDao.updateProduct(pro);
                    }
                });
                t.start();
            }
        });
    }
}