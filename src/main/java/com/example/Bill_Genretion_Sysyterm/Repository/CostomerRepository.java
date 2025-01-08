package com.example.Bill_Genretion_Sysyterm.Repository;

import com.example.Bill_Genretion_Sysyterm.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostomerRepository extends JpaRepository<Customer,Integer> {
}
