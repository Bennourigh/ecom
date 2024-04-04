package org.mgb.orderservice.DTOs;


import lombok.Data;
import org.mgb.orderservice.entities.ProductItem;
import org.mgb.orderservice.enums.OrderStatus;
import org.mgb.orderservice.model.Customer;

import java.util.Date;
import java.util.List;
@Data

public class OrderDTO {

    private Long id;

    private Date createdAt;
    private OrderStatus status;

    private Long customerId;

    private Customer customer;

    private List<ProductItem> productItems;

}
