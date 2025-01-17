package com.example.Bill_Genretion_Sysyterm.Repository;

import com.example.Bill_Genretion_Sysyterm.Model.Admin;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
//    Optional<Admin> findByAdminName(String admin_type);
}
