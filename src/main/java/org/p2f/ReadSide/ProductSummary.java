package org.p2f.ReadSide;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummary {
    @Id
    private String productId;
    private Double price;
    private Integer stock;
    private String description;
}