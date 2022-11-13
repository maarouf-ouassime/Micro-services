package org.openlab.openlabcustomerservice.services;

import org.openlab.openlabcustomerservice.dto.CustomerRequestDto;
import org.openlab.openlabcustomerservice.dto.CustomerResponseDto;
import org.openlab.openlabcustomerservice.entities.Customer;
import org.openlab.openlabcustomerservice.mappers.CustomerMapper;
import org.openlab.openlabcustomerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequestDto) {

        Customer customer = customerMapper.customerRequestDtoToCustomer(customerRequestDto);
        Customer savedCustomer = customerRepository.save(customer);

        CustomerResponseDto customerResponseDto = customerMapper.customerToCustomerResponseDto(savedCustomer);

        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto getCustomer(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerResponseDto customerResponseDto = customerMapper.customerToCustomerResponseDto(customer);
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto update(String id, CustomerRequestDto customerRequestDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        Customer updatedCustomer =  customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDto(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDto> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtos =
                customers.stream().
                        map(customer -> customerMapper.customerToCustomerResponseDto(customer)).collect(Collectors.toList());
        return customerResponseDtos;
    }
}
