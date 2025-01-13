package com.example.Bill_Genretion_Sysyterm.Controller;

import com.example.Bill_Genretion_Sysyterm.DTO.Infromation;
import com.example.Bill_Genretion_Sysyterm.Model.Admin;
import com.example.Bill_Genretion_Sysyterm.Model.Product;
import com.example.Bill_Genretion_Sysyterm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("Add-Product")
    public Product Add_product(@RequestBody Product p){
       return productService.save(p);
    }
    @PostMapping("/stokRepot")
    public String sendStockReport(@RequestParam String adminEmail) {
        try {
            productService.sendStockReport(adminEmail);
            return "Stock report email sent to " + adminEmail;
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
