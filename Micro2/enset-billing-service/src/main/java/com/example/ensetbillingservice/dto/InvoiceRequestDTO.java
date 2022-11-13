package com.example.ensetbillingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class InvoiceRequestDTO {

    private  BigDecimal amount;
    private  String customerId;
}
