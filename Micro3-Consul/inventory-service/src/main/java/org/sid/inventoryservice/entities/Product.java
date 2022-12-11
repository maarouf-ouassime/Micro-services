package org.sid.inventoryservice.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    double price;
    int quantity;
}
