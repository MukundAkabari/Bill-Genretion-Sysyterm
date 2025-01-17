package com.example.Bill_Genretion_Sysyterm.Service;

import com.example.Bill_Genretion_Sysyterm.Model.Admin;
import com.example.Bill_Genretion_Sysyterm.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    public void add_addmin(Admin admin) {
        adminRepository.save(admin);
    }
}
