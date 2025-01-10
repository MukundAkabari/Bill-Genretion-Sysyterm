package com.example.Bill_Genretion_Sysyterm.Service;

import com.example.Bill_Genretion_Sysyterm.Model.Product;
import com.example.Bill_Genretion_Sysyterm.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product save(Product p) {
       return productRepository.save(p);
    }
}
