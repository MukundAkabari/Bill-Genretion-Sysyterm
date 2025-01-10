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
    private int productId;

    @Column(name = "product_name")
    private String productName; // Renamed to camelCase

    @Column(name = "price")
    private int price;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "thresholdquantity")
    private int thresholdQuantity;

    @OneToMany(mappedBy = "product")
    private Set<Bill_Items> billItems;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getThresholdQuantity() {
        return thresholdQuantity;
    }

    public void setThresholdQuantity(int thresholdQuantity) {
        this.thresholdQuantity = thresholdQuantity;
    }

    public Set<Bill_Items> getBillItems() {
        return billItems;
    }

    public void setBillItems(Set<Bill_Items> billItems) {
        this.billItems = billItems;
    }
}
