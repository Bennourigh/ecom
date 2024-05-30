package org.p2f.querry;


import org.axonframework.eventhandling.EventHandler;
import org.p2f.ReadSide.OrderSummary;
import org.p2f.ReadSide.ProductSummary;
import org.p2f.Repository.OrderSummaryRepository;
import org.p2f.Repository.ProductSummaryRepository;
import org.p2f.api.OrderCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderProjector {
    private final ProductSummaryRepository repositoryP;
    private final OrderSummaryRepository repository;
    public OrderProjector(ProductSummaryRepository repositoryP, OrderSummaryRepository repository) {
        this.repositoryP = repositoryP;
        this.repository = repository;
    }
    @EventHandler
    public void on(OrderCreatedEvent event){
        OrderSummary summary = new OrderSummary(
                event.getOrderId(),
                event.getPrice(),
                event.getQty(),
                event.getProductId()
        );
        repository.save(summary);
        ProductSummary product = repositoryP.findById(summary.getProductId()).get();
        product.setStock(product.getStock() - summary.getQte());
        repositoryP.save(product);
    }
    //    Todo @QueryHandler or not actually i am going with the JPA offerings to the application
//    @QueryHandler
//    public Iterable<OrderSummary> getOrders(){
//        return repository.findAll();
//    }
//    @QueryHandler
//    public OrderSummary getOrder(String orderId){
//        return repository.findById(orderId).orElse(null);
//    }
}