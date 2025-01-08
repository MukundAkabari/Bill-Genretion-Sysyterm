package com.example.Bill_Genretion_Sysyterm.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int AdminId;
    @Column(name = "name")
    private String Name;
    @Column(name = "email")
    private int Email;
    @Column(name = "mobil_no")
    private int MobilNo;

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getEmail() {
        return Email;
    }

    public void setEmail(int email) {
        Email = email;
    }

    public int getMobilNo() {
        return MobilNo;
    }

    public void setMobilNo(int mobilNo) {
        MobilNo = mobilNo;
    }
}
