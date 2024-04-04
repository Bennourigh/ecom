package org.mgb.inventoryservice.services;

import lombok.AllArgsConstructor;
import org.hibernate.query.Page;
import org.mgb.inventoryservice.DTOs.ProductDTO;
import org.mgb.inventoryservice.entities.Product;
import org.mgb.inventoryservice.mappers.ProductMapperimpl;
import org.mgb.inventoryservice.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor

public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    ProductMapperimpl productMapper;

    @Override
    public Page getAllProducts(int page, int size) {
        List<Product> products = productRepository.findAll();


        return (Page) products.stream().map(product -> productMapper.fromProduct(product)).collect(Collectors.toList());

    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();


        return products.stream().map(product -> productMapper.fromProduct(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productMapper.fromProduct(productRepository.findById(id).get());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productMapper.fromProductDTO(productDTO);
        Product savedproduct =productRepository.save(product);

        return productMapper.fromProduct(savedproduct) ;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = productMapper.fromProductDTO(productDTO);
        Product savedproduct =productRepository.save(product);
        return productMapper.fromProduct(savedproduct) ;
    }
    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


}
