package com.geekbrains.ru.hibernate.service;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.domain.ProductSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface ProductService {
    List<ProductEntity> findAll();
    ProductEntity findById(Long id);
    ProductEntity save(ProductEntity productEntity);
    Page<ProductEntity> findAllByPageAndCategory(Pageable pageable, String categoryAlias);
    Page<ProductEntity> findAllBySearchCondition(ProductSearchCondition searchCondition) ;
    ProductEntity saveWithImage(ProductEntity product, MultipartFile image);

    void deleteById(Long productId);


}
