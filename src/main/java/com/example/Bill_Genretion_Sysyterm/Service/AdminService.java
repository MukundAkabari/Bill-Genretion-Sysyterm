package com.example.Bill_Genretion_Sysyterm.Service;

import com.example.Bill_Genretion_Sysyterm.Model.Admin;
import com.example.Bill_Genretion_Sysyterm.Model.Product;
import com.example.Bill_Genretion_Sysyterm.Repository.AdminRepository;
import com.example.Bill_Genretion_Sysyterm.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ProductRepository productRepository;

    public Admin add_addmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void add_product(String name,int Quntity) {
       Optional<Product> product=productRepository.findByProductName(name);
        Product product1=product.get();
        product1.setProductQuantity(product1.getProductQuantity()+Quntity);
        productRepository.save(product1);
    }

    public void setthreholequntity(String name, int Quntity) {
        Optional<Product> product=productRepository.findByProductName(name);
        Product product1=product.get();
       product1.setThresholdQuantity(Quntity);
        productRepository.save(product1);
    }

}
