package com.example.Bill_Genretion_Sysyterm.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bills")
public class Bills {
    @Id

    @Column(name = "bill_id")
    private String  BillId;
    @Column(name = "date")
    private String Date;
    @Column(name = "total_amount")
    private int TotalAmount;
    @Column(name = "gst_amount")
    private int GstAmount;
    @Column(name = "final_amount")
    private int FinalAmount;
    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "bills")
    private Set<Bill_Items> billItems;



    public Set<Bill_Items> getBillItems() {
        return billItems;
    }

    public void setBillItems(Set<Bill_Items> billItems) {
        this.billItems = billItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getGstAmount() {
        return GstAmount;
    }

    public void setGstAmount(int gstAmount) {
        GstAmount = gstAmount;
    }

    public int getFinalAmount() {
        return FinalAmount;
    }

    public void setFinalAmount(int finalAmount) {
        FinalAmount = finalAmount;
    }
}
