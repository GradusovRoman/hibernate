package com.geekbrains.ru.hibernate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForProductDto {
    private Long id;
    private Double price;
    private  String name;
    private String imageLink;
}
