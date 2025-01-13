package com.example.Bill_Genretion_Sysyterm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.Bill_Genretion_Sysyterm")
//@EnableJpaRepositories(basePackages = "com.example.Bill_Genretion_Sysyterm.Repository")
public class BillGenretionSysytermApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillGenretionSysytermApplication.class, args);
	}

}
