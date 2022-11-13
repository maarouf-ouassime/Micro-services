package org.sid.inventoryservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullProduct",types = Product.class)
public interface ProductProjection extends Projection {
    public Long getId();
    public String getName();
    public double getPrice();
    public double getQuantity();
}

