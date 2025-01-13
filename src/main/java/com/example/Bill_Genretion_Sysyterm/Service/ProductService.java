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
    private JavaMailSender mailSender;
    @Autowired
    static ProductRepository productRepository;




    public Product save(Product p) {
       return productRepository.save(p);
    }

    public  void sendProductStockEmail(String toEmail) {
        List<Product> productStocks = productRepository.findAll();
        System.out.println();
        StringBuilder emailBody = new StringBuilder("Product Stock Report:\n\n");
        for (Product product : productStocks) {
            emailBody.append("Product: ").append(product.getProductName())
                    .append(" | Stock Quantity: ").append(product.getProductQuantity()).append("\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Product Stock Report");
        message.setText(emailBody.toString());

         mailSender.send(message);
    }
}
