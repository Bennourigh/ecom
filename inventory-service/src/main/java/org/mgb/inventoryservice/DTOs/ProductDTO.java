package org.mgb.inventoryservice.DTOs;

import lombok.*;


@Data
public class ProductDTO {

    private Long id;
    private String name;
    private double price;
    private int quantity;
    private boolean state;
}
