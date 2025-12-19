package com.application.services;


import com.application.entities.ProductEntity;
import com.application.exception.ProductNotFoundException;
import com.application.mapper.ProductMapper;
import com.application.repositories.ProductRepository;
import com.example.model.ProductCreateRequest;
import com.example.model.ProductResponse;
import com.example.model.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServices {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductCreateRequest dto){
        ProductEntity productEntity = productRepository.save
                (productMapper.toEntityForCreation(dto));
        ProductResponse product = productMapper.toDto(productEntity);
        return product;
    }

    public List<ProductResponse> getAllProducts(){
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductResponse> products = productMapper.toDtoList(productEntityList);
        return products;
   }

    public  ProductResponse getProductById(Integer id){
       ProductEntity  productEntity = productRepository.findById(id).orElseThrow(()->
       {throw  new ProductNotFoundException("No Product Exits With Id "+ id);});
       return productMapper.toDto(productEntity);
    }

    public ProductResponse updateProduct(Integer id, ProductUpdateRequest dto){
        ProductEntity product = productRepository.findById(id).
                orElseThrow(() ->{throw new ProductNotFoundException("No Product with Id "+ id);});
        product =productMapper.toEntityForUpdate(dto,product);
        product = productRepository.save(product);
        return  productMapper.toDto(product);
    }

    public  String deleteProduct(Integer id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("No Product by Id "+id);
        }
        productRepository.deleteById(id);
        return "Product with Id "+id+" Succesfully Deleted ";
    }



}
