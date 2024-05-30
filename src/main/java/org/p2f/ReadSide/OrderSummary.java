package org.p2f.ReadSide;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderSummary {
    @Id
    private String orderId;
    private Double price;
    private Integer qte;
    private String productId;
    public OrderSummary(String orderID, Double price, Integer qte, String productId) {
        this.orderId = orderID;
        this.price = price;
        this.qte = qte;
        this.productId = productId;
    }
}