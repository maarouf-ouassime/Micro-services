package org.sid.billingservice.web;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController
{
    private BillRepository billRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRepository productItemRepository;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository, CustomerRestClient customerRestClient, ProductItemRepository productItemRepository, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRepository = productItemRepository;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id)
    {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(productItemRestClient.getProductById(pi.getProductId()));
        });
        return bill;
    }
}
