package com.example.Bill_Genretion_Sysyterm.Controller;

import com.example.Bill_Genretion_Sysyterm.DTO.Infromation;
import com.example.Bill_Genretion_Sysyterm.Model.Product;
import com.example.Bill_Genretion_Sysyterm.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("Add-Product")
    public Product Add_product(@RequestBody Product p){
       return productService.save(p);
    }
    //this methos is optonal because it for test
    @PostMapping("data")
    public void data(@RequestBody Infromation i){
        productService.data(i);
    }
}
