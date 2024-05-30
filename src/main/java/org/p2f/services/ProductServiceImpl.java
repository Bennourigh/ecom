package org.p2f.services;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.p2f.ReadSide.ProductSummary;
import org.p2f.api.*;
import org.p2f.controller.ProductsController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final CommandGateway commandGateway;
    public ProductServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    @Override
    public ResponseEntity<String> addProduct(ProductsController.addProductSummary summary) {
        try {
            if (summary != null) {
                String productId = UUID.randomUUID().toString().replace("-", "");
                productId = productId.substring(0, productId.length() / 2);
                AddProductCommand command = new AddProductCommand(
                        productId,
                        summary.getPrice(),
                        summary.getStock(),
                        summary.getDescription()
                );
                commandGateway.send(command);
                return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product fields are empty", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<String> updateProduct(ProductSummary summary) {
        UpdateProductCommand command = new UpdateProductCommand(
                summary.getProductId(),
                summary.getPrice(),
                summary.getStock(),
                summary.getDescription()
        );
        commandGateway.send(command);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }
    @Override
    public void removeProduct(String productId) {
        DeleteProductCommand command = new DeleteProductCommand(productId);
        commandGateway.send(command);
    }
    @Override
    public ResponseEntity<String> updateProductPrice(String productId, double price) {
        UpdateProductPriceCommand command = new UpdateProductPriceCommand(productId, price);
        commandGateway.send(command);
        return new ResponseEntity<>("price update has been processed",HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> updateProductStock(String productId, int stock) {
        UpdateProductStockCommand command = new UpdateProductStockCommand(productId, stock);
        commandGateway.send(command);
        return new ResponseEntity<>("price update has been processed",HttpStatus.OK);
    }
}
