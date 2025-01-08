package com.example.Bill_Genretion_Sysyterm.Repository;

import com.example.Bill_Genretion_Sysyterm.Model.Bill_Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bill_ItemsRepository extends JpaRepository<Bill_Items,Integer> {
}
