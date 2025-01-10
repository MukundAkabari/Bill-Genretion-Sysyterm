package com.example.Bill_Genretion_Sysyterm.Controller;

import com.example.Bill_Genretion_Sysyterm.DTO.Infromation;
import com.example.Bill_Genretion_Sysyterm.Model.Bills;
import com.example.Bill_Genretion_Sysyterm.Model.Customer;
import com.example.Bill_Genretion_Sysyterm.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("C-bill")
    public void genrete_bill(@RequestBody Infromation i){
        customerService.save(i);
    }
}
