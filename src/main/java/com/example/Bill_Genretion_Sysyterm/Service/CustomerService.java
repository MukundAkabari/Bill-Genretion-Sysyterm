package com.example.Bill_Genretion_Sysyterm.Service;

import com.example.Bill_Genretion_Sysyterm.DTO.Infromation;
import com.example.Bill_Genretion_Sysyterm.Model.Bill_Items;
import com.example.Bill_Genretion_Sysyterm.Model.Bills;
import com.example.Bill_Genretion_Sysyterm.Model.Customer;
import com.example.Bill_Genretion_Sysyterm.Model.Product;
import com.example.Bill_Genretion_Sysyterm.Repository.BillRepository;
import com.example.Bill_Genretion_Sysyterm.Repository.Bill_ItemsRepository;
import com.example.Bill_Genretion_Sysyterm.Repository.CostomerRepository;
import com.example.Bill_Genretion_Sysyterm.Repository.ProductRepository;

import com.razorpay.Document;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import static com.twilio.Twilio.init;


@Service
@Slf4j
public class CustomerService {
    @Autowired
    CostomerRepository costomerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    Bill_ItemsRepository billItemsRepository;

    @Autowired
    JavaMailSender mailSender;

    @Value("${TWILIO_ACCOUNT_SID}")
            String account_sid;

    @Value("${TWILIO_AUTH_TOCKEN}")
            String Auth_Token;

    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
            String Number;

    int totalAmount = 0;

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @PostConstruct
    private void setup(){
        Twilio.init(account_sid, Auth_Token);
    }

    public void save(Infromation i) throws RazorpayException {
        // Create customer
        Customer customer = new Customer();
        customer.setCoustomerName(i.getCname());
        customer.setEmailId(i.getEmailid());
        customer.setMobilNo(i.getMobileno());

        String currency = "INR";

        // Calculate total amount and GST
        List<Bill_Items> billItemsList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : i.getProduct().entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();

            // Get product details
            Optional<Product> productOptional = productRepository.findByProductName(productName);
            if (productOptional.isEmpty()) {
                throw new RuntimeException("Product not found: " + productName);
            }

            Product product = productOptional.get();
            int gst = (product.getPrice() * 18) / 100;
            int finalPrice = quantity * product.getPrice();
            totalAmount += finalPrice;

            // Create Bill_Item
            Bill_Items billItems = new Bill_Items();
            billItems.setQuantity(quantity);
            billItems.setToatalPrice(finalPrice);
            billItems.setPricePerUnit(product.getPrice());
            billItems.setProduct(product);

            // Update product quantity in the product table
            if(product.getProductQuantity()<product.getThresholdQuantity()) {
                StringBuilder emailbody=new StringBuilder("Stoke requiment\n\n");
                emailbody.append("product name").append(product.getProductName()).append("\n")
                                .append("price").append(product.getPrice()).append("\n")
                                .append("Quintity").append(product.getProductQuantity()).append("\n")
                                .append("Threshold Quantity:").append(product.getThresholdQuantity());

                SimpleMailMessage message=new SimpleMailMessage();
                message.setTo("akabarimukund36@gmail.com");
                message.setSubject("for Stoke menegment");
                message.setText(emailbody.toString());
                mailSender.send(message);
            }else {
                product.setProductQuantity(product.getProductQuantity() - quantity);
            }

            billItemsList.add(billItems);
        }

        int totalGst = (totalAmount * 18) / 100;

        // Integrate Razorpay
        RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", totalAmount * 100); // Amount in paise
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", UUID.randomUUID().toString());

        Order order = razorpayClient.orders.create(orderRequest);
        String razorpayOrderId = order.get("id");

        // Create bill
        Bills bills = new Bills();
        bills.setCustomer(customer);
        bills.setDate(LocalDate.now().toString());
        bills.setGstAmount(totalGst);
        bills.setFinalAmount(totalAmount);
        bills.setTotalAmount(totalAmount + totalGst);
        bills.setBillId(razorpayOrderId); // Set Razorpay order ID

        // Set bill to bill items
        for (Bill_Items item : billItemsList) {
            item.setBills(bills);
        }
        customer.setBills(bills);
        // Save all data to tables
        costomerRepository.save(customer);
        billRepository.save(bills);
        billItemsRepository.saveAll(billItemsList);


        int formesgamonur=totalAmount+totalGst;
        //send messsge to cutomer
        StringBuilder smsMessege=new StringBuilder("order complete\n\n");
        smsMessege.append("you have to paid "+formesgamonur+" and keep shoping for us");
        Message message = Message.creator(
                new PhoneNumber("+" + i .getMobileno()), // Target mobile number
                new PhoneNumber(Number),         // Your Twilio phone number
                String.valueOf(smsMessege)                       // Message content
        ).create();

    }
}

