package org.sid.orderservice.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = IDENTITY)
    Long id;
    Date createdAt;
    OrderStatus status;
    Long customerId;
    @Transient
    Customer customer;
    @OneToMany(mappedBy = "order")
    List<ProductItem> productItems;
}
