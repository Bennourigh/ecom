package org.p2f.WriteSide;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.p2f.api.*;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Product {
    @AggregateIdentifier
    private String productId;
    private Double price;
    private Integer stock;
    private String description;

    public Product(){}

    @CommandHandler
    public void handel(@NotNull AddProductCommand cmd){
        apply(new ProductAddedEvent(
                cmd.getProductId(),
                cmd.getPrice(),
                cmd.getStock(),
                cmd.getDescription()
        ));
    }
    @EventSourcingHandler
    public void on(@NotNull ProductAddedEvent event){
        productId = event.getProductId();
        price = event.getPrice();
        stock = event.getStock();
        description = event.getDescription();
    }
    @CommandHandler
    public void handle(@NotNull UpdateProductCommand cmd){
        apply(new ProductUpdatedEvent(
                cmd.getProductId(),
                cmd.getStock(),
                cmd.getPrice(),
                cmd.getDescription()
        ));
    }
    @EventSourcingHandler
    public void on(@NotNull ProductUpdatedEvent event){
        stock = event.getStock();
        price = event.getPrice();
    }
    @CommandHandler
    public void handle(@NotNull UpdateProductStockCommand cmd){
        apply(new ProductStockUpdatedEvent(
                cmd.getProductId(),
                cmd.getStock()
        ));
    }
    @EventSourcingHandler
    public void on(@NotNull ProductStockUpdatedEvent event){
        productId = event.getProductId();
        stock = event.getStock();
    }
    @CommandHandler
    public void handle(@NotNull UpdateProductPriceCommand cmd){
        apply(new ProductPriceUpdatedEvent(
                cmd.getProductId(),
                cmd.getPrice()
        ));
    }
    @EventSourcingHandler
    public void on(@NotNull ProductPriceUpdatedEvent event){
        productId = event.getProductId();
        price = event.getPrice();
    }
    @CommandHandler
    public void handel(@NotNull DeleteProductCommand cmd){
        apply(new ProductDeletedEvent(
                cmd.getProductId()
        ));
    }
    @EventSourcingHandler
    public void on(@NotNull ProductDeletedEvent event){
        productId = event.getProductId();
    }
}