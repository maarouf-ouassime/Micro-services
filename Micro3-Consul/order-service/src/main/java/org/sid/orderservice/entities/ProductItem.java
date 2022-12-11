package org.sid.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.sid.orderservice.model.Product;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductItem {
    @Id @GeneratedValue(strategy = IDENTITY)
    Long id;
    Long productId;
    @Transient
    Product product;
    int quantity;
    double price;
    double discount;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Order order;
}
