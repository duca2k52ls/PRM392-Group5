package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqllite.DAO.ProductDAO;
import com.example.sqllite.Models.Products;

import java.util.List;

public class AdminScreenActivity extends AppCompatActivity {


    EditText edt_ProductId, edt_Productname, edt_SupplierId, edt_CategoryId, edt_QuantityPerUnit, edt_UnitPrice, edt_ProductImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

        edt_ProductId = findViewById(R.id.Proid_txt);
        edt_Productname = findViewById(R.id.Proname_txt);
        edt_SupplierId = findViewById(R.id.sup_txt);
        edt_CategoryId = findViewById(R.id.cate_txt);
        edt_QuantityPerUnit = findViewById(R.id.Proquantity_txt2);
        edt_UnitPrice = findViewById(R.id.Proprice_txt3);
        edt_ProductImage = findViewById(R.id.Proimage_txt4);
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

        ((Button)findViewById(R.id.btn_list)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProductDAO customerDao = db.productDAO();
                        if(edt_ProductId.getText().toString().isEmpty() && edt_Productname.getText().toString().isEmpty()){
                            List<Products> products = customerDao.getAll();
                            ((TextView) findViewById(R.id.tv_show)).setText(products.toString());
                        }else if(edt_ProductId.getText().toString().isEmpty() && !edt_Productname.getText().toString().isEmpty()) {
                            Products products = customerDao.findByOnlyName(edt_Productname.getText().toString());
                            ((TextView)findViewById(R.id.tv_show)).setText(products.toString());
                        }else if(!edt_ProductId.getText().toString().isEmpty() && edt_Productname.getText().toString().isEmpty()){
                            Products products = customerDao.findByOnlyID(Integer.parseInt(edt_ProductId.getText().toString()));
                            ((TextView)findViewById(R.id.tv_show)).setText(products.toString());
                        }else{
                            Products customers = customerDao.findByName(Integer.parseInt(edt_ProductId.getText().toString()), edt_Productname.getText().toString());
                            ((TextView)findViewById(R.id.tv_show)).setText(customers.toString());
                        }
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

        ((Button)findViewById(R.id.btn_update)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProductDAO productDao = db.productDAO();
                        int customerIdUpdate = Integer.parseInt(edt_ProductId.getText().toString());
                        Products pro = productDao.getByIds(customerIdUpdate);
                        productDao.deleteProduct(pro);
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

        ((Button)findViewById(R.id.btn_Home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AdminScreenActivity.this, adminHome.class);
                        startActivity(intent);
                    }
                });
                t.start();
            }
        });
    }
}