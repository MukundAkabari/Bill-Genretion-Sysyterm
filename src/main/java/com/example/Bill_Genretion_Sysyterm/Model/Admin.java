package com.example.Bill_Genretion_Sysyterm.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;
    @Column(name = "name")
    private String Name;
    @Column(name = "admin_type")
    private String admin_type;
    @Column(name = "email")
    private String email;
    @Column(name = "mobil_no",unique = true)
    private String mobilNo;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdmin_type() {
        return admin_type;
    }

    public void setAdmin_type(String admin_type) {
        this.admin_type = admin_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilNo() {
        return mobilNo;
    }

    public void setMobilNo(String mobilNo) {
        this.mobilNo = mobilNo;
    }
}
