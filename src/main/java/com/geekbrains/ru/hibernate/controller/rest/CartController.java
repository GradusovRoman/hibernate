package com.geekbrains.ru.hibernate.controller.rest;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.repository.ProductRepository;
import com.geekbrains.ru.hibernate.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.geekbrains.ru.hibernate.domain.constant.RequestNameConstant.*;

@RestController
@RequestMapping(API_V1 + USER + "/{userId}" + CART)
@RequiredArgsConstructor
public class CartController {

    private ProductRepository productRepository;
    private ProductService productService;
    private HashMap<Long, List<ProductEntity>> cartsRepository;

    public CartController(ProductRepository productRepository,
                          HashMap<Long, List<ProductEntity>> cartsRepository) {
        this.productRepository = productRepository;

        this.cartsRepository = cartsRepository;
    }

    @GetMapping
    public List<ProductEntity> getUserCart(@PathVariable long userId) {
        return cartsRepository.get(userId);
    }


    @PostMapping(PRODUCT + "/{productId}")
    public void addProductToCart(@PathVariable long userId, @PathVariable long productId) {
        if (cartsRepository.get(userId) == null) {
            cartsRepository.put(userId, new ArrayList<>());
        }
            cartsRepository.get(userId).add(productRepository.findById(productId).get());
    }

    @DeleteMapping(PRODUCT + "/{productId}")
    public void deleteProductFromCart(@PathVariable long userId, @PathVariable long productId) {
        Optional<ProductEntity> product = cartsRepository.get(userId).stream().
                filter(p -> p.getId() == productId).findFirst();
        product.ifPresent(value -> cartsRepository.get(userId).remove(value));
    }


}

