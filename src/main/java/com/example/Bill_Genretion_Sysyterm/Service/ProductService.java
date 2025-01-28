package com.example.Bill_Genretion_Sysyterm.Service;

import com.example.Bill_Genretion_Sysyterm.Model.Product;
import com.example.Bill_Genretion_Sysyterm.Repository.AdminRepository;
import com.example.Bill_Genretion_Sysyterm.Repository.ProductRepository;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Value("${admin.email}")
    String adminMail;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    ProductRepository productRepository;




    public Product save(Product p) {
       return productRepository.save(p);
    }

    public void sendStockReport() throws IOException {


         //Create the email content
//        StringBuilder emailBody = new StringBuilder("Product Stock Report:\n\n");
//        for (Product product : products) {
//            emailBody.append("Product ID: ").append(product.getProductId()).append("\n")
//                    .append("Name: ").append(product.getProductName()).append("\n")
//                    .append("Price: ").append(product.getPrice()).append("\n")
//                    .append("Quantity: ").append(product.getProductQuantity()).append("\n")
//                    .append("Threshold Quantity: ").append(product.getThresholdQuantity()).append("\n\n");
//        }
        //this code to write recode in csv file

            Date date=new Date(System.currentTimeMillis());
           SimpleDateFormat sm=new SimpleDateFormat("dd-mm-yyyy");
           String format=sm.format(date);
           try(Writer writer=new FileWriter("E:\\mukund project\\Bill-Genretion-Sysyterm\\src\\main\\resources\\Report\\stock-report("+format+").csv");
               ICsvBeanWriter iCsvBeanWriter=new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE)){

               String[] headers={"productId","productName","productQuantity"};
               iCsvBeanWriter.writeHeader(headers);

               // Fetch all products
               List<Product> products = productRepository.findAll();

               for (Product product:products){
                   iCsvBeanWriter.write(product,headers);
               }

           }catch (IOException e){
               e.printStackTrace();
           }


             try {
                 MimeMessage message=mailSender.createMimeMessage();
                 MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
                 mimeMessageHelper.setTo(adminMail);
                 mimeMessageHelper.setText("stoke report");
                 mimeMessageHelper.setSubject("Stock Report");

                 Date date1=new Date(System.currentTimeMillis());
                 SimpleDateFormat sm1=new SimpleDateFormat("dd-mm-yyyy");
                 String formate=sm1.format(date1);

                 String filename="stock-report("+formate+").csv";

                 FileSystemResource fileSystemResource=new FileSystemResource("E:\\mukund project\\Bill-Genretion-Sysyterm\\src\\main\\resources\\Report\\"+filename);
                 mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);

                 mailSender.send(message);

             } catch (MessagingException e) {
                 throw new RuntimeException(e);
             }

    }
}
