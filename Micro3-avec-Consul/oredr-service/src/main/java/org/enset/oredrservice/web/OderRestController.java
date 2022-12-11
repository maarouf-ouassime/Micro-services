package org.enset.oredrservice.web;

import org.enset.oredrservice.entities.Order;
import org.enset.oredrservice.model.Customer;
import org.enset.oredrservice.model.Product;
import org.enset.oredrservice.repository.OrderRepository;
import org.enset.oredrservice.repository.ProductItemRepository;
import org.enset.oredrservice.services.CustomerRestClientService;
import org.enset.oredrservice.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OderRestController {

    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            Product product=inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }
}
