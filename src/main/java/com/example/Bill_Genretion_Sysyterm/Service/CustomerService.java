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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CostomerRepository costomerRepository;

   @Autowired
    ProductRepository productRepository;
   @Autowired
    BillRepository billRepository;
   @Autowired
    Bill_ItemsRepository billItemsRepository;

    public void save(Infromation i) {
        Optional<Product> product = productRepository.findByProductName(i.getPname());
        System.out.println(" ");
        Product product1=product.get();
        Customer customer=new Customer();
        customer.setCoustomerName(i.getCname());
        customer.setEmailId(i.getEmailid());
        customer.setMobilNo(i.getMobileno());


        Bills bills=new Bills();
        bills.setCustomer(customer);
        bills.setDate(LocalDate.now().toString());
        int gst=product1.getPrice()*18/100;
        bills.setGstAmount(gst);
        int FinalPrice=i.getP_quntity()*product1.getPrice();
        bills.setFinalAmount(FinalPrice);
        bills.setTotalAmount(FinalPrice+gst);

        Bill_Items billItems=new Bill_Items();
        billItems.setBills(bills);
        billItems.setQuantity(i.getP_quntity());
        billItems.setToatalPrice(gst+FinalPrice);
        billItems.setPricePerUnit(product1.getPrice());
        billItems.setProduct(product1);

        customer.setBills(bills);
        product1.setProductQuantity(product1.getProductQuantity()-i.getP_quntity());

        costomerRepository.save(customer);
        billRepository.save(bills);
        billItemsRepository.save(billItems);
        System.out.println(" ");
    }
}
