package org.mgb.inventoryservice.mappers;

import org.mgb.inventoryservice.DTOs.ProductDTO;
import org.mgb.inventoryservice.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperimpl {

    public ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }
    public Product fromProductDTO(ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}