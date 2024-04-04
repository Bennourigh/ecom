package org.mgb.orderservice.services;

import lombok.AllArgsConstructor;
import org.mgb.inventoryservice.entities.Product;
import org.mgb.orderservice.DTOs.OrderDTO;
import org.mgb.orderservice.entities.Order;
import org.mgb.orderservice.mappers.OrderMapperImpl;
import org.mgb.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    OrderMapperImpl OrderMapperImpl;
    private OrderRepository orderRepository;

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {

        Order order = OrderMapperImpl.fromOrderDTO(orderDTO);
        Order savedorder = orderRepository.save(order);

        return OrderMapperImpl.fromOrder(savedorder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }




    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order order = OrderMapperImpl.fromOrderDTO(orderDTO);
        Order savedorder = orderRepository.save(order);

        return OrderMapperImpl.fromOrder(savedorder);
    }



    @Override
    public OrderDTO getOrderById(Long id) {
        return OrderMapperImpl.fromOrder(orderRepository.findById(id).get());

    }

    @Override
    public List<OrderDTO> getOrders() {
        List<Order> orders=orderRepository.findAll();

        return  orders.stream().map(order -> OrderMapperImpl.fromOrder(order)).collect(Collectors.toList());
    }
}
