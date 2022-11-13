package com.example.ensetbillingservice;

import com.example.ensetbillingservice.dto.InvoiceRequestDTO;
import com.example.ensetbillingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class EnsetBillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsetBillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService invoiceService) {
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(1000),"id1"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(2000),"id2"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(3000),"id3"));

        };
    }
}

