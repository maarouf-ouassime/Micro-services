package org.sid.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer {
    Long id;
    String name;
    String email;
}
