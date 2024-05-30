package org.p2f.WriteSide;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.p2f.api.CreateOrderCommand;
import org.p2f.api.OrderCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Order {
    @AggregateIdentifier
    private String orderId;
    private Double price;
    private Integer qty;
    private String productId;
    public Order(){}
    @CommandHandler
    public Order(CreateOrderCommand cmd){
        apply(new OrderCreatedEvent(
                cmd.getOrderId(),
                cmd.getPrice(),
                cmd.getQty(),
                cmd.getProductId()
        ));
    }
    @EventSourcingHandler
    public void on(OrderCreatedEvent event){
        orderId = event.getOrderId();
        price = event.getPrice();
        qty = event.getQty();
        productId = event.getProductId();

    }
}