package com.example.Bill_Genretion_Sysyterm.Service;

import com.example.Bill_Genretion_Sysyterm.Model.Product;
import com.example.Bill_Genretion_Sysyterm.Repository.AdminRepository;
import com.example.Bill_Genretion_Sysyterm.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    ProductRepository productRepository;




    public Product save(Product p) {
       return productRepository.save(p);
    }

    public void sendStockReport(String adminEmail) {
        // Fetch all products
        List<Product> products = productRepository.findAll();

        // Create the email content
        StringBuilder emailBody = new StringBuilder("Product Stock Report:\n\n");
        for (Product product : products) {
            emailBody.append("Product ID: ").append(product.getProductId()).append("\n")
                    .append("Name: ").append(product.getProductName()).append("\n")
                    .append("Price: ").append(product.getPrice()).append("\n")
                    .append("Quantity: ").append(product.getProductQuantity()).append("\n")
                    .append("Threshold Quantity: ").append(product.getThresholdQuantity()).append("\n\n");
        }

        // Create and send the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("Stock Report");
        message.setText(emailBody.toString());

        mailSender.send(message);
    }
}
