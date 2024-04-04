package org.mgb.orderservice.web;

import lombok.AllArgsConstructor;

import org.mgb.orderservice.DTOs.OrderDTO;
import org.mgb.orderservice.entities.Order;
import org.mgb.orderservice.mappers.OrderMapperImpl;
import org.mgb.orderservice.model.Customer;
import org.mgb.orderservice.model.Product;
import org.mgb.orderservice.repository.OrderRepository;
import org.mgb.orderservice.repository.ProductItemRepository;
import org.mgb.orderservice.services.CustomerRestClientService;
import org.mgb.orderservice.services.InventoryRestClientService;
import org.mgb.orderservice.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController@AllArgsConstructor
public class OrderRestController {

    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;
    private OrderMapperImpl orderMapper;
    private OrderService orderService;


    @GetMapping("/Order/{id}")
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
    @GetMapping("/Orders")
    public List<OrderDTO> getOrders(){
        return orderService.getOrders();
    }
    @PostMapping("/Order")
    public OrderDTO saveOrder(OrderDTO orderDTO){
        return orderService.addOrder(orderDTO);
    }

    @DeleteMapping("/Order/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
    @PutMapping("/Order/{id}")
    public OrderDTO updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        orderDTO.setId(id);
        return orderService.updateOrder(orderDTO);
    }



}