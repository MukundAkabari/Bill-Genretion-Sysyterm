package com.example.Bill_Genretion_Sysyterm.Controller;

import com.example.Bill_Genretion_Sysyterm.Model.Admin;
import com.example.Bill_Genretion_Sysyterm.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Admin")
public class AdminConroller {

    @Autowired
    AdminService adminService;
    @PostMapping("add_admin")
    public void add_admin(Admin admin){
        adminService.add_addmin(admin);
    }
}
