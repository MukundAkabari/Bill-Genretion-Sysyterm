package com.example.Bill_Genretion_Sysyterm.Repository;

import com.example.Bill_Genretion_Sysyterm.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
//    @Query("SELECT p FROM Product p WHERE p.product_name = :productName")
//    Product findbyname(@Param("productName")String name);
//    Product findByName(String name);

//    @Query("SELECT p FROM product p WHERE p.product_name = :productName")
//    Product findByProductName(@Param("productName") String productName);
    Optional<Product> findByProductName(String productName);



}



