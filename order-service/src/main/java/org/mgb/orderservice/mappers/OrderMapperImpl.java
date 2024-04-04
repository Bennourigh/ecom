package org.mgb.orderservice.mappers;

import org.mgb.orderservice.DTOs.OrderDTO;
import org.mgb.orderservice.entities.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderMapperImpl {
    public OrderDTO fromOrder(Order order) {
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderDTO, orderDTO);

        return orderDTO;
    }

    public Order fromOrderDTO(OrderDTO orderDTO) {
        Order order=new Order();
        BeanUtils.copyProperties(orderDTO, order);

        return order;
    }

}
