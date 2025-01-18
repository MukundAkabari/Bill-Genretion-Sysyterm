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

import com.razorpay.*;
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

//    @Value("${TWILIO_ACCOUNT_SID}")
//            String account_sid;
//
//    @Value("${TWILIO_AUTH_TOCKEN}")
//            String Auth_Token;
//
//    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
//            String Number;

    int totalAmount = 0;

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    //this constructor for set twilo sid and auth token
//    @PostConstruct
//    private void setup(){
//        Twilio.init(account_sid, Auth_Token);
//    }

    public void save(Infromation i) throws RazorpayException, InterruptedException {
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
        // this is to create oeder object
//        JSONObject orderRequest = new JSONObject();
//        orderRequest.put("amount", totalAmount * 100); // Amount in paise
//        orderRequest.put("currency", currency);
//        orderRequest.put("receipt", UUID.randomUUID().toString());
//
//        Order order = razorpayClient.orders.create(orderRequest);
//        String razorpayOrderId = order.get("id");




        int formesgamonur=totalAmount+totalGst;


        //PaymentLink paymentLink

        JSONObject pymentlinkrequest= new JSONObject();
        pymentlinkrequest.put("amount",formesgamonur*100);
        pymentlinkrequest.put("currency",currency);
        pymentlinkrequest.put("description","pyment for order");
        pymentlinkrequest.put("customer", new JSONObject()
                .put("name", i.getCname())
                .put("email", i.getEmailid())
                .put("contact", i.getMobileno()));
        pymentlinkrequest.put("notify", new JSONObject().put("sms", true).put("email", true));
        pymentlinkrequest.put("callback_url", "https://yourdomain.com/payment/success");
        pymentlinkrequest.put("callback_method", "get");

// Create Payment Link
        JSONObject paymentLinkResponse = razorpayClient.paymentLink.create(pymentlinkrequest).toJson();
        String paymentLinkId = paymentLinkResponse.getString("id"); // Razorpay Payment Link ID
        String paymentStatus= paymentLinkResponse.getString( "status");
        String paymentLink = paymentLinkResponse.getString("short_url"); // Get the sh


        // Create bill
        Bills bills = new Bills();
        bills.setCustomer(customer);
        bills.setDate(LocalDate.now().toString());
        bills.setGstAmount(totalGst);
        bills.setFinalAmount(totalAmount);
        bills.setTotalAmount(totalAmount + totalGst);
        bills.setBillId(paymentLinkId);
        bills.setStatus(paymentStatus);// Set Razorpay order ID

        // Set bill to bill items
        for (Bill_Items item : billItemsList) {
            item.setBills(bills);
        }
        customer.setBills(bills);


            String status;
            do {
                // Fetch the payment link details
                JSONObject paymentLinkDetails = razorpayClient.paymentLink.fetch(paymentLinkId).toJson();
                status = paymentLinkDetails.getString("status"); // Status can be "paid", "cancelled", etc.

                if ("paid".equalsIgnoreCase(status)) {
                    log.info("Payment received for Payment Link ID: {}", paymentLinkId);
                    break;
                } else if ("cancelled".equalsIgnoreCase(status)) {
                    log.warn("Payment link was cancelled: {}", paymentLinkId);
                    break;
                }

                // Wait for a few seconds before checking again
                Thread.sleep(5000); // 5 seconds delay

            } while (!"paid".equalsIgnoreCase(status) && !"cancelled".equalsIgnoreCase(status));

            // Update the bill status in the database
                bills.setStatus(status.toUpperCase());

        // Save all data to tables

        costomerRepository.save(customer);
        billRepository.save(bills);
        billItemsRepository.saveAll(billItemsList);


        //this is for test to send msg using twilio
        //send messsge to cutomer
//        StringBuilder smsMessege=new StringBuilder("order complete\n\n");
//        smsMessege.append("You need to pay ₹").append(totalAmount + totalGst).append(". ")
//                .append("Please complete your payment using the following link: ").append(paymentLink)
//                .append("\nThank you for shopping with us!");
//        Message message = Message.creator(
//                new PhoneNumber("+" + i .getMobileno()), // Target mobile number
//                new PhoneNumber(Number),         // Your Twilio phone number
//                String.valueOf(smsMessege)                       // Message content
//        ).create();
    }
}

