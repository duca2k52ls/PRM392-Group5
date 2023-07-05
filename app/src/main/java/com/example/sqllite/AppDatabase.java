package com.example.sqllite;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sqllite.DAO.ProductDAO;
import com.example.sqllite.Models.Products;

@Database(entities = {Products.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDAO productDAO();
}
