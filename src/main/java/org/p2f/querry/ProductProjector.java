package org.p2f.querry;

import org.axonframework.eventhandling.EventHandler;
import org.p2f.ReadSide.ProductSummary;
import org.p2f.Repository.ProductSummaryRepository;
import org.p2f.api.ProductAddedEvent;
import org.p2f.api.ProductDeletedEvent;
import org.p2f.api.ProductUpdatedEvent;
import org.springframework.stereotype.Component;
//import java.util.*;

@Component
public class ProductProjector {
    private final ProductSummaryRepository repository;
    public ProductProjector(ProductSummaryRepository repository) {
        this.repository = repository;
    }
    @EventHandler
    public void on(ProductAddedEvent event){
        ProductSummary summary = new ProductSummary(
                event.getProductId(),
                event.getPrice(),
                event.getStock(),
                event.getDescription()
        );
        repository.save(summary);
    }
    @EventHandler
    public void on(ProductUpdatedEvent event){
        ProductSummary summary = repository.findById(event.getProductId()).orElse(null);
        if(summary !=null){
            summary.setPrice(event.getPrice());
            summary.setStock(event.getStock());
            summary.setDescription(event.getDescription());
        }else {
            throw new RuntimeException("Product not updated, unable to find product");
        }
    }
    @EventHandler
    public void on(ProductDeletedEvent event){
        repository.deleteById(event.getProductId());
    }
//    Todo @QueryHandler or not actually i am going with the JPA offerings to the application
//    what's practical in using a concept if the business needs are not satisfied or even required such tracking !!
//    public Iterable<ProductSummary> handle(GetProductsQuery query){return repository.findAll();}
}