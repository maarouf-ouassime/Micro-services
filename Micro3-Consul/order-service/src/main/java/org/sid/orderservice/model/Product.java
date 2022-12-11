package org.sid.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data @AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Product {
    Long id;
    String name;
    double price;
    int quantity;
}
