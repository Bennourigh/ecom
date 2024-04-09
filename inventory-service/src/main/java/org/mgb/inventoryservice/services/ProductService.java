package org.mgb.inventoryservice.services;

import org.hibernate.query.Page;
import org.mgb.inventoryservice.DTOs.ProductDTO;
import org.mgb.inventoryservice.entities.Product;

import java.util.List;

public interface ProductService {


    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);

    void deleteProduct(Long id);
}
