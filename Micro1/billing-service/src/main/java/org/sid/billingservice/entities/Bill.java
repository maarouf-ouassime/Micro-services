package org.sid.billingservice.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.sid.billingservice.model.Customer;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill
{
    @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    Long id;
    Date billingDate;
    @OneToMany(mappedBy = "bill")
    Collection<ProductItem> productItems;
    Long customerId;
    @Transient
    Customer customer;


}
