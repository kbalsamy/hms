package com.hygieia.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.ProductDto;
import com.hygieia.app.Models.Product;
import com.hygieia.app.Repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    
    public Product SaveProdcut(ProductDto productDto){

        Product product=new Product();

        product.setId(productDto.getProductId());
        product.setName(productDto.getName());
        product.setPrice((productDto.getPrice()));

        Product newProduct = productRepository.save(product);

        return newProduct;

        



    }
    
}
