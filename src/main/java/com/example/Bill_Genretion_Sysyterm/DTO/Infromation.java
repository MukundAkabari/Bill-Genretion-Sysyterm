package com.example.Bill_Genretion_Sysyterm.DTO;

import java.util.Map;

public class Infromation {
    private String cname;
    //to get multipal product
    private Map<String,Integer> product;
    private int mobileno;
    private String emailid;


    public Map<String, Integer> getProduct() {
        return product;
    }

    public void setProduct(Map<String, Integer> product) {
        this.product = product;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }



    public int getMobileno() {
        return mobileno;
    }

    public void setMobileno(int mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
