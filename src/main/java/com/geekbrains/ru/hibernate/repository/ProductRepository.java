package com.geekbrains.ru.hibernate.repository;

import com.geekbrains.ru.hibernate.domain.CategoryEntity;
import com.geekbrains.ru.hibernate.domain.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findAllByCategories(CategoryEntity category, Pageable pageable);

    Page<ProductEntity> findByPriceGreaterThan(Pageable pageable,Double minPrice);
    Page<ProductEntity> findByPriceLessThan(Pageable pageable,Double maxPrice);
    Page<ProductEntity> findByPriceBetween (Pageable pageable, Double minPrice,Double maxPrice);

    Page<ProductEntity> findByTitleLike(Pageable pageable,String title);
}
