package org.openlab.openlabcustomerservice.services;

import org.openlab.openlabcustomerservice.dto.CustomerRequestDto;
import org.openlab.openlabcustomerservice.dto.CustomerResponseDto;

import java.util.List;

public interface CustomerService
{
    CustomerResponseDto save(CustomerRequestDto customerRequestDto);
    CustomerResponseDto getCustomer(String id);
    CustomerResponseDto update(String id, CustomerRequestDto customerRequestDto);
    List<CustomerResponseDto> listCustomers();
}
