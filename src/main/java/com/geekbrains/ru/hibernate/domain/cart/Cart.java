package com.geekbrains.ru.hibernate.domain.cart;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.domain.dto.ProductDto;

import java.util.List;

public interface Cart {
    void addProduct(Long id);
    void deleteProduct(Long id);
    void clear();
    List<ProductEntity> getProducts();
}
