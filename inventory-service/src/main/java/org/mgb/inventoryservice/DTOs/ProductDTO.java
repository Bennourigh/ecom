package org.mgb.inventoryservice.DTOs;

import lombok.*;

import java.util.Set;


@Data
public class ProductDTO {

    private Long id;
    private String name;
    private double price;
    private int quantity;
    private boolean state;

}
