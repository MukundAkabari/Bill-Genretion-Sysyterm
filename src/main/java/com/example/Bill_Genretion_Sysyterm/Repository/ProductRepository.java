package com.example.Bill_Genretion_Sysyterm.Repository;

import com.example.Bill_Genretion_Sysyterm.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
