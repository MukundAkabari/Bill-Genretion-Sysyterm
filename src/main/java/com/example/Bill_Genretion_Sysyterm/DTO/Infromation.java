package com.example.Bill_Genretion_Sysyterm.DTO;

public class Infromation {
    private String cname;
    private  String pname;
    private int p_quntity;
    private int mobileno;
    private String emailid;
    public int getP_quntity() {
        return p_quntity;
    }

    public void setP_quntity(int p_quntity) {
        this.p_quntity = p_quntity;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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
