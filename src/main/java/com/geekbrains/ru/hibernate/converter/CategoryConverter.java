package com.geekbrains.ru.hibernate.converter;

import com.geekbrains.ru.hibernate.domain.CategoryEntity;
import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.domain.dto.CategoryDto;
import com.geekbrains.ru.hibernate.domain.dto.ProductDto;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryConverter {

    public static Set<CategoryDto> convertToDto (Set<CategoryEntity> entity){
        return entity.stream()
                .map(CategoryConverter::convertToDto)
                .collect(Collectors.toSet());
    }

    public static CategoryDto convertToDto (CategoryEntity entity){
        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .products(ProductConverter.convertToDto(entity.getProducts())  )
                .build();
    }
}
