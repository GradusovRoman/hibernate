package com.geekbrains.ru.hibernate.controller.rest;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.domain.ProductSearchCondition;
import com.geekbrains.ru.hibernate.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.geekbrains.ru.hibernate.domain.constant.RequestNameConstant.API_V1;
import static com.geekbrains.ru.hibernate.domain.constant.RequestNameConstant.PRODUCT;

@RestController("restProductController")
@AllArgsConstructor
@RequestMapping(API_V1 + PRODUCT)
public class ProductController {

    private ProductService productService;

    @PostMapping
    public Page<ProductEntity> getProductsByCondition(@RequestBody ProductSearchCondition searchCondition) {

        return productService.findAllBySearchCondition(searchCondition);
    }

    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PutMapping(path = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> saveProduct(@RequestBody ProductEntity entity,
                                      @RequestParam(required = false) MultipartFile image) {
        productService.saveWithImage(entity, image);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public int deleteProductById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return HttpStatus.OK.value();
    }
}
