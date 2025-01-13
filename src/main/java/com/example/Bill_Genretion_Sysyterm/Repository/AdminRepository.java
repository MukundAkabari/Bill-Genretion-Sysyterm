package com.example.Bill_Genretion_Sysyterm.Repository;

import ch.qos.logback.core.model.INamedModel;
import com.example.Bill_Genretion_Sysyterm.Model.Admin;
import com.example.Bill_Genretion_Sysyterm.Model.Product;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaAttributeConverter<Admin, Integer> {
    Optional<Admin> findByAdminName(String admin_type);
}
