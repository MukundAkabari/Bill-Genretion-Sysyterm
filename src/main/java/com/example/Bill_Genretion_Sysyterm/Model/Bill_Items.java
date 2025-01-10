package com.example.Bill_Genretion_Sysyterm.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "bill_items")
public class Bill_Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int ItemId;
    @Column(name = "quantity")
    private int Quantity;
    @Column(name = "price_per_unit")
    private int PricePerUnit;
    @Column(name = "total_price")
    private int ToatalPrice;
    @ManyToOne
    @JoinColumn(name = "bill_id",nullable = false)
    private Bills bills;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")  // Foreign key to Products table
    private Product product;

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public Bills getBills() {
        return bills;
    }

    public void setBills(Bills bills) {
        this.bills = bills;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPricePerUnit() {
        return PricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        PricePerUnit = pricePerUnit;
    }

    public int getToatalPrice() {
        return ToatalPrice;
    }

    public void setToatalPrice(int toatalPrice) {
        ToatalPrice = toatalPrice;
    }
}
