package com.example.Bill_Genretion_Sysyterm.Controller;

import com.example.Bill_Genretion_Sysyterm.Model.Admin;
import com.example.Bill_Genretion_Sysyterm.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Admin")
public class AdminConroller {

    @Autowired
    AdminService adminService;
    @PostMapping("add_admin")
    public Admin add_admin(@RequestBody Admin admin){
        return adminService.add_addmin(admin);
        }
        @PutMapping("add_product")
    public void add_product(@RequestParam(value = "name") String name,@RequestParam(value = "Quntity") int Quntity){
        adminService.add_product(name,Quntity);
        }
        @PutMapping("chang_thresholdquantity")
    public void chang_thresholdquantity(@RequestParam(value = "name") String name,@RequestParam(value = "Quntity") int Quntity){
        adminService.setthreholequntity(name,Quntity);
        }
        @DeleteMapping("delete_product")
    public void delete(@RequestParam(value = "name") String name){
        adminService.delete(name);
        }
}
