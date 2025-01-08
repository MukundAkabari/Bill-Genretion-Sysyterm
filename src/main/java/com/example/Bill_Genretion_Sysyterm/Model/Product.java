package com.example.Bill_Genretion_Sysyterm.Model;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int ProductId;
    @Column(name = "product_name")
    private String ProductName;
    @Column(name = "price")
    private int Price;
    @Column(name = "product_quantity")
    private int ProductQuantity;
    @Column(name = "thresholdquantity")
    private int ThresholdQuantity;
    @OneToMany(mappedBy = "product")
    private Set<Bill_Items> billItems;

    public Set<Bill_Items> getBillItems() {
        return billItems;
    }

    public void setBillItems(Set<Bill_Items> billItems) {
        this.billItems = billItems;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }

    public int getThresholdQuantity() {
        return ThresholdQuantity;
    }

    public void setThresholdQuantity(int thresholdQuantity) {
        ThresholdQuantity = thresholdQuantity;
    }
}
