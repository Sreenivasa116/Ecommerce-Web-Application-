package com.application.controllers;

import com.application.services.ProductsServices;
import com.example.api.ProductsApi;
import com.example.model.ProductCreateRequest;
import com.example.model.ProductResponse;
import com.example.model.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductsApi {

    @Autowired
    private ProductsServices productsServices;

    @Override
    public ResponseEntity<ProductResponse> createProduct
            (ProductCreateRequest productCreateRequest) {
            ProductResponse product = productsServices.createProduct(productCreateRequest);
            if(product != null )
                return ResponseEntity.status(HttpStatus.CREATED).body(product);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productsServices.getAllProducts();
        if(products != null && !products.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(products);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(Integer id) {
        ProductResponse product = productsServices.getProductById(id);
        if(product != null)
            return ResponseEntity.status(HttpStatus.OK).body(product);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<ProductResponse> updateProductById
            (Integer id,ProductUpdateRequest productUpdateRequest) {
        ProductResponse product = productsServices.updateProduct(id,productUpdateRequest);
        if(product != null)
            return ResponseEntity.status(HttpStatus.OK).body(product);
      return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<String> deleteProductById(Integer id) {
        String result = productsServices.deleteProduct(id);
        if (result != null && !result.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(result);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
