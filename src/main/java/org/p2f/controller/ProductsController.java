package org.p2f.controller;


import lombok.Getter;
import org.p2f.ReadSide.ProductSummary;
import org.p2f.Repository.ProductSummaryRepository;
import org.p2f.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class ProductsController {
    private final ProductService productService;
    //TODO remove repository usage in service to respect encapsulation
    private final ProductSummaryRepository repositoryP;
    public record addProductSummary(@Getter Double price, @Getter Integer stock, @Getter String description) {}

    public ProductsController(ProductService productService,ProductSummaryRepository repositoryP1) {
        this.productService = productService;
        this.repositoryP = repositoryP1;
    }
    @PostMapping("/products/add-product")
    public ResponseEntity<String> handle(@RequestBody addProductSummary summary) {
        return productService.addProduct(summary);
    }
    @PostMapping("/products/{productId}/update-product-price/{price}")
    public ResponseEntity<String> handle(@PathVariable("productId") String productId,@PathVariable("price") double price) {
        return productService.updateProductPrice(productId, price);
    }

    @PutMapping("/products/update-product")
    public ResponseEntity<String> handle(@RequestBody ProductSummary summary) {
        return productService.updateProduct(summary);
    }
    @DeleteMapping("/products/delete-product/{productId}")
    public void handleDPC(@PathVariable("productId") String productId) {
        productService.removeProduct(productId);
    }
    @GetMapping("/products/product-id/{productId}")
    public Optional<ProductSummary> getProduct(@PathVariable(value = "productId") String productId) {
        return repositoryP.findById(productId);
    }
    @GetMapping("/products/all-products")
    public List<ProductSummary> getAllProduct() {
        return repositoryP.findAll();
    }

    @GetMapping("/products/all-products/{description}")
    public List<ProductSummary> getAllProductByDescription(@PathVariable String description) {
        return repositoryP.findAllByDescriptionContaining(description);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}