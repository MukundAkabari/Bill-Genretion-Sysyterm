package com.example.Bill_Genretion_Sysyterm.Service;

import com.example.Bill_Genretion_Sysyterm.DTO.Infromation;
import com.example.Bill_Genretion_Sysyterm.Model.Product;
import com.example.Bill_Genretion_Sysyterm.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product save(Product p) {
       return productRepository.save(p);
    }

    public void data(Infromation i) {
        System.out.println("Searching for product with name: " + i.getPname());
        Optional<Product> product = productRepository.findByProductName(i.getPname());
        if (product.isPresent()) {
            System.out.println("Product found: " + product.get());
        } else {
            System.out.println("Product not found!");
        }



    }
}
