package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        TextView ProductId = (TextView)findViewById(R.id.text_id);
        TextView ProductName = (TextView)findViewById(R.id.text_name);
        TextView SupplierId = (TextView)findViewById(R.id.text_supid);
        TextView CategoryId = (TextView)findViewById(R.id.text_cateid);
        TextView QuantityPerUnit = (TextView)findViewById(R.id.text_quantity);
        TextView UnitPrice = (TextView)findViewById(R.id.text_price);
        TextView ProductImage = (TextView)findViewById(R.id.text_image);
        Button btnAdd = (Button)findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionclass();
                try {
                    if(connection != null){
                        String sqladd = "Insert into Products values('" + ProductId.getText().toString() + "','" + ProductName.getText().toString() + "','" + SupplierId.getText().toString() + "','" + CategoryId.getText().toString() + "','" + QuantityPerUnit.getText().toString() + "','" + UnitPrice.getText().toString() + "','" + ProductImage.getText().toString() + "')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqladd);
                    }
                }catch (Exception exception){
                    Log.e("Error", exception.getMessage());
                }
            }
        });
    }

    @SuppressLint("NewApi")
    public Connection connectionclass() {
        Connection con = null;
        String ip = "127.0.0.1", port = "1433", username = "sa", password = "123", databasename = "PRM392";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
        return con;
    }

}