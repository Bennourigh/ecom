package org.p2f.controller;

import lombok.Getter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.jetbrains.annotations.NotNull;
import org.p2f.ReadSide.OrderSummary;
import org.p2f.Repository.OrderSummaryRepository;
import org.p2f.api.CreateOrderCommand;
import org.p2f.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrdersController {
    private final OrderSummaryRepository repository;
    private final CommandGateway commandGateway;
    private final ProductService productService;
    public record addOrder(@Getter Double price,@Getter Integer qte, @Getter String productId){}
    public OrdersController(OrderSummaryRepository repository, CommandGateway commandGateway, ProductService productService) {
        this.repository = repository;
        this.commandGateway = commandGateway;
        this.productService = productService;
    }
    @PostMapping("/orders/add-order")
    public ResponseEntity<String> handle(@NotNull @RequestBody addOrder orderSummary){
        OrderSummary summary = new OrderSummary();
        String orderId = UUID.randomUUID().toString().replace("-", "");
        orderId = orderId.substring(0, orderId.length() / 2);
        summary.setOrderId(orderId);
        summary.setQte(orderSummary.getQte());
        summary.setPrice(orderSummary.getPrice());
        summary.setProductId(orderSummary.getProductId());
        CreateOrderCommand command = new CreateOrderCommand(
                summary.getOrderId(),
                summary.getPrice(),
                summary.getQte(),
                summary.getProductId()
        );
        commandGateway.send(command);
        //Todo update product stock with the quantity or just handel this business from the front end !? :done
        productService.updateProductStock(summary.getProductId(),summary.getQte());
        return new ResponseEntity<>("order operation has been processed",HttpStatus.OK);
    }
    @GetMapping("/orders/all-orders")
    public List<OrderSummary> getAllOrder(){
        return repository.findAll();
    }
    @GetMapping("/orders/orderById/{orderId}")
    public OrderSummary getOrderById(@PathVariable String orderId){
        if(repository.findById(orderId).isPresent()) {
            return repository.findById(orderId).get();
        }else throw (new IllegalArgumentException("Invalid order id: "));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}