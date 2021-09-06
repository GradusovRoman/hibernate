package com.geekbrains.ru.hibernate.converter;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.domain.dto.ProductDto;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductConverter {

    public static Set<ProductDto> convertToDto (Set<ProductEntity> entity){
        return  entity.stream()
                .map(ProductConverter::convertToDto)
                .collect(Collectors.toSet());
    }
    public static ProductDto convertToDto (ProductEntity entity){
       return  ProductDto.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .title(entity.getTitle())
                .categories(CategoryConverter.convertToDto(entity.getCategories()))
                .build();
    }
}
