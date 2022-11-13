package org.openlab.openlabcustomerservice.web;

import org.openlab.openlabcustomerservice.dto.CustomerRequestDto;
import org.openlab.openlabcustomerservice.dto.CustomerResponseDto;
import org.openlab.openlabcustomerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class CustomerRestAPI {

    private  CustomerService customerService;

    public CustomerRestAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers")
    public List<CustomerResponseDto> listCustomers() {
        return customerService.listCustomers();
    }

    @PostMapping(path = "/customers")
    public CustomerResponseDto save(@RequestBody CustomerRequestDto customerRequestDto) {
        customerRequestDto.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDto);
    }

    @GetMapping(path = "/customers/{id}")
    public CustomerResponseDto getCustomer(@PathVariable String id) {
        return customerService.getCustomer(id);
    }
}
