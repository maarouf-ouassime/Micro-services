package org.sid.customerservice.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id @GeneratedValue(strategy = IDENTITY)
    Long id;
    String name;
    String email;
}
