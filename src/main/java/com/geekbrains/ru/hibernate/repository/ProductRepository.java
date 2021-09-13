package com.geekbrains.ru.hibernate.repository;

import com.geekbrains.ru.hibernate.domain.CategoryEntity;
import com.geekbrains.ru.hibernate.domain.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//@Repository
public interface ProductRepository /*extends JpaRepository<ProductEntity, Long> */{

//    Page<ProductEntity> findAllByCategories(CategoryEntity category, Pageable pageable);
//
//    Page<ProductEntity> findByPriceGreaterThan(Pageable pageable,Double minPrice);
//    Page<ProductEntity> findByPriceLessThan(Pageable pageable,Double maxPrice);
//    Page<ProductEntity> findByPriceBetween (Pageable pageable, Double minPrice,Double maxPrice);
//
//    Page<ProductEntity> findByTitleIsLikeIgnoreCase (Pageable pageable,String title);

    Page<ProductEntity> findAllByCategories(CategoryEntity category, Pageable pageable);

    ProductEntity findNativeProduct();

    Page<ProductEntity> findAll(Pageable pageRequest);

    ProductEntity save(ProductEntity product);

    void deleteById(Long productId);

    Optional<ProductEntity> findById(long id);

    List<ProductEntity> findAll();
}
