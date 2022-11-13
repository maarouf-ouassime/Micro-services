package org.openlab.openlabcustomerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlab.openlabcustomerservice.entities.Customer;

import java.io.Serializable;


@Data @NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto implements Serializable {
    private  String id;
    private  String name;
    private  String email;
}
