package org.p2f.services;

import org.p2f.ReadSide.ProductSummary;
import org.p2f.controller.ProductsController;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    @Transactional
    ResponseEntity<String> addProduct(ProductsController.addProductSummary summary);
    @Transactional
    ResponseEntity<String> updateProduct(ProductSummary summary);
    @Transactional
    void removeProduct(String productId);

    ResponseEntity<String> updateProductPrice(String productId, double price);

    ResponseEntity<String> updateProductStock(String productId, int stock);

}
