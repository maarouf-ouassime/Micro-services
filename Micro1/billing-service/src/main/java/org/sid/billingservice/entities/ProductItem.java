package org.sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.sid.billingservice.model.Product;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductItem
{
    @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    Long id;
    double price;
    double quantity;
    Long productId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    Bill bill;
    @Transient
    Product product;
}
