package com.hygieia.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.ProductDto;
import com.hygieia.app.Models.Product;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.ProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {


    @Autowired
    private ProductService productService;


@PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto orderDto) {

        try{
            Product product = productService.SaveProdcut(orderDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Product created successfully", product));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));

        }

    }
    
}
