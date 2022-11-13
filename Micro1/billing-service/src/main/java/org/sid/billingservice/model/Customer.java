package org.sid.billingservice.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer
{
    Long id;
    String name;
    String email;
}
