package org.mgb.inventoryservice.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mgb.inventoryservice.DTOs.ProductDTO;
import org.mgb.inventoryservice.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j


@RequestMapping( "/api")
public class ProductRestController {
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> listProducts(){
        return productService.getAllProducts();
    }


    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id){
        return productService.getProductById(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")

    @PostMapping("/products")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/products/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        productDTO.setId(id);
        return productService.updateProduct(productDTO);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/auth")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
}
