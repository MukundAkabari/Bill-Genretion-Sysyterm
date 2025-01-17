package com.example.Bill_Genretion_Sysyterm.Model;

import jakarta.persistence.*;

@Entity
@Table(name ="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coustomer_id")
    private int CoustomerId;
    @Column(name = "coustomer_name", nullable = false)
    private String CoustomerName;
    @Column(name = "mobil_no" , unique = true)
    private String MobilNo;
    @Column(name = "email_id")
    private String EmailId;

    @OneToOne(cascade = CascadeType.ALL)
    private Bills bills;

    public Bills getBills() {
        return bills;
    }

    public void setBills(Bills bills) {
        this.bills = bills;
    }

    public int getCoustomerId() {
        return CoustomerId;
    }

    public void setCoustomerId(int coustomerId) {
        CoustomerId = coustomerId;
    }

    public String getCoustomerName() {
        return CoustomerName;
    }

    public void setCoustomerName(String coustomerName) {
        CoustomerName = coustomerName;
    }

    public String getMobilNo() {
        return MobilNo;
    }

    public void setMobilNo(String mobilNo) {
        MobilNo = mobilNo;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }
}
