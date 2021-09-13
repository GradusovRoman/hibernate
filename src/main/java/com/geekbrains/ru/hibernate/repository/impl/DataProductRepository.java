package com.geekbrains.ru.hibernate.repository.impl;

import com.geekbrains.ru.hibernate.domain.CategoryEntity;
import com.geekbrains.ru.hibernate.domain.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DataProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(nativeQuery = true, value = "select * from product where id = #{principal.username}")
    Page<ProductEntity> findAllByCategories(CategoryEntity category, Pageable pageable);
}
