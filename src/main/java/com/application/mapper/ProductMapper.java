package com.application.mapper;

import com.application.entities.ProductEntity;
import com.example.model.ProductCreateRequest;
import com.example.model.ProductResponse;
import com.example.model.ProductUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;


@Mapper(componentModel = "spring")
public interface ProductMapper {

        //Create Product
        @Mapping(source = "name",target = "name")
        @Mapping(source = "description",target = "description")
        @Mapping(source = "price",target = "price")
        @Mapping(target = "createdAt",expression = "java(java.time.LocalDateTime.now())")
        @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
        @Mapping(source = "stockQuantity",target = "stockQuantity")
        ProductEntity toEntityForCreation(ProductCreateRequest productCreateRequest);

        //Update Product
        @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
        @Mapping(source = "name",target = "name")
        @Mapping(source = "description",target = "description")
        @Mapping(source = "price",target = "price")
        @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
        @Mapping(source = "stockQuantity",target = "stockQuantity")
        ProductEntity toEntityForUpdate
        (ProductUpdateRequest updateRequest,@MappingTarget ProductEntity productEntity);

        @Mapping(source = "id",target = "id")
        @Mapping(source = "name",target = "name")
        @Mapping(source = "description",target = "description")
        @Mapping(source = "price",target = "price")
        @Mapping(source = "createdAt" ,target = "createdAt")
        @Mapping(source = "updatedAt",target = "updatedAt")
        @Mapping(source = "stockQuantity",target = "stockQuantity")
        ProductResponse toDto(ProductEntity product);
        List<ProductResponse> toDtoList (List<ProductEntity> productEntityList);


}
