package org.p2f.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderId;
    private String productId;
    private Double price;
    private Integer Qte;
    //Todo private Date fromDate;
    //Todo private Date toDate;
}