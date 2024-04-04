package org.mgb.orderservice.services;

import org.mgb.orderservice.DTOs.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO addOrder(OrderDTO orderDTO);


    void deleteOrder(Long id);

    OrderDTO updateOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getOrders();
}
