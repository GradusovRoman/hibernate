package com.geekbrains.ru.hibernate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntitySearchByPrice {
    private Double minPrice;
    private Double maxPrice;
}
