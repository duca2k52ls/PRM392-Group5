package com.example.sqllite.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Products {
    @PrimaryKey(autoGenerate = true)
    private int ProductID;

    @ColumnInfo(name = "ProductName")
    private String ProductName;

    @ColumnInfo(name = "SupplierID")
    private int SupplierID;

    @ColumnInfo(name = "CategoryID")
    private int CategoryID;

    @ColumnInfo(name = "QuantityPerUnit")
    private int QuantityPerUnit;

    @ColumnInfo(name = "UnitPrice")
    private double UnitPrice;

    @ColumnInfo(name = "ProductImage")
    private String ProductImage;

    public Products(int productID, String productName, int supplierID, int categoryID, int quantityPerUnit, double unitPrice, String productImage) {
        ProductID = productID;
        ProductName = productName;
        SupplierID = supplierID;
        CategoryID = categoryID;
        QuantityPerUnit = quantityPerUnit;
        UnitPrice = unitPrice;
        ProductImage = productImage;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int supplierID) {
        SupplierID = supplierID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(int quantityPerUnit) {
        QuantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    @Override
    public String toString() {
        return "Products{" +
                "ProductID=" + ProductID +
                ", ProductName='" + ProductName + '\'' +
                ", SupplierID=" + SupplierID +
                ", CategoryID=" + CategoryID +
                ", QuantityPerUnit=" + QuantityPerUnit +
                ", UnitPrice=" + UnitPrice +
                ", ProductImage='" + ProductImage + '\'' +
                '}';
    }
}
