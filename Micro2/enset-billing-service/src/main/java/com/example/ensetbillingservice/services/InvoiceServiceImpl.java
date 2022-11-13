package com.example.ensetbillingservice.services;

import com.example.ensetbillingservice.dto.InvoiceRequestDTO;
import com.example.ensetbillingservice.dto.InvoiceResponseDTO;
import com.example.ensetbillingservice.entities.Customer;
import com.example.ensetbillingservice.entities.Invoice;
import com.example.ensetbillingservice.exceptions.CustomerNotFoundException;
import com.example.ensetbillingservice.mappers.InvoiceMapper;
import com.example.ensetbillingservice.openfeign.CustomerRestClient;
import com.example.ensetbillingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private  InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }


    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Customer customer = null;
        try {
            customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer not found");
        }
        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice invoiceSaved = invoiceRepository.save(invoice);
        invoiceSaved.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoiceSaved);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices = invoiceRepository.findAllByCustomerId(customerId);
        for (Invoice invoice : invoices) {
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        List<InvoiceResponseDTO> invoiceResponseDTOS = invoices.
                stream().
                map(invoice -> invoiceMapper.fromInvoice(invoice)).
                collect(Collectors.toList());
        return invoiceResponseDTOS;
    }

    @Override
    public List<InvoiceResponseDTO> listInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice : invoices) {
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            System.out.println("id : "+invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
            return invoices.
                    stream().
                    map(inv -> invoiceMapper.fromInvoice(inv)).
                    collect(Collectors.toList());

    }
}
