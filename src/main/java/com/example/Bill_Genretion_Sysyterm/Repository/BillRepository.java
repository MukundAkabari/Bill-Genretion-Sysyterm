package com.example.Bill_Genretion_Sysyterm.Repository;

import com.example.Bill_Genretion_Sysyterm.Model.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bills,Integer> {
}
