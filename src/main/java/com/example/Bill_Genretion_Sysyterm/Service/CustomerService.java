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
import java.util.*;

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
   int totalamount=0;

    public void save(Infromation i) {
        //create customer
        Customer customer=new Customer();
        customer.setCoustomerName(i.getCname());
        customer.setEmailId(i.getEmailid());
        customer.setMobilNo(i.getMobileno());

        //create bill
        Bills bills=new Bills();
        bills.setCustomer(customer);
        bills.setDate(LocalDate.now().toString());

        //bill list add the multipal bill
        List<Bill_Items> billItemsList=new ArrayList<>();
        //get multipal product using map
        for(Map.Entry<String ,Integer> entry : i.getProduct().entrySet()){

            //get product name and quntity
            String productname=entry.getKey();
            int quntity = entry.getValue();

            //for get product details
            Optional<Product> productOptinal = productRepository.findByProductName(productname);
            if(productOptinal.isEmpty()){
                throw  new RuntimeException("product not found : " +productname);
            }

            //create product object set value
            Product product1=productOptinal.get();
            //for culclate gst and final amount
            int gst=(product1.getPrice()*18)/100;
            int finalprice=quntity*product1.getPrice();


            //to get total amount of all product
            totalamount+=quntity*product1.getPrice();

            //cerate bill_item object
            Bill_Items billItems=new Bill_Items();
            billItems.setBills(bills);
            billItems.setQuantity(quntity);
            billItems.setToatalPrice(finalprice);
            billItems.setPricePerUnit(product1.getPrice());
            billItems.setProduct(product1);

            //set product quntity in product tabel
            product1.setProductQuantity(product1.getProductQuantity()-quntity);

            //set value in bill arry
            billItemsList.add(billItems);
            System.out.println();

            }

        int totalgst=(totalamount*18)/100;
        //set amount in bill
        bills.setGstAmount(totalgst);
        bills.setFinalAmount(totalamount);
        bills.setTotalAmount(totalamount+totalgst);

        //set bill id in customer
        customer.setBills(bills);
        //save all data in tables
        costomerRepository.save(customer);
        billRepository.save(bills);
        billItemsRepository.saveAll(billItemsList);


    }

}

