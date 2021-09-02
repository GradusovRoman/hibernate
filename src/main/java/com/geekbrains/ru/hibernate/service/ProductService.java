package com.geekbrains.ru.hibernate.service;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@Service
public interface ProductService {
    List<ProductEntity> findAll();

    ProductEntity findById(long id);

    ProductEntity save(ProductEntity productEntity);

    Page<ProductEntity> findAllByPageAndCategory(Pageable pageable, String categoryAlias);

    ProductEntity saveWithImage(ProductEntity product, MultipartFile image);

    void deleteById(Long productId);


}
