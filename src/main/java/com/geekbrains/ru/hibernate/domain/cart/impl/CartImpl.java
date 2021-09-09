package com.geekbrains.ru.hibernate.domain.cart.impl;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.domain.cart.Cart;
import com.geekbrains.ru.hibernate.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartImpl implements Cart {

    private final ProductService productService;
    private final List<ProductEntity> products;

    public CartImpl( ProductService productService, List<ProductEntity> products) {
        this.productService = productService;
        this.products =  new ArrayList<>();
    }

    @Override
    public void addProduct(Long id) {
        products.add(productService.findById(id));
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = productService.findById(id);
        products.remove(product);
    }

    @Override
    public void clear() {
    products.clear();
    }

    @Override
    public List<ProductEntity> getProducts() {
        return products;
    }
}
