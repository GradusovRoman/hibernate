package com.geekbrains.ru.hibernate.service;

import com.geekbrains.ru.hibernate.domain.CategoryEntity;
import com.geekbrains.ru.hibernate.domain.dto.CategoryTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    List<CategoryEntity> findAll();

    CategoryEntity findById(Long id);

    CategoryEntity findByAlias(String alias);

    CategoryEntity save(CategoryEntity category);

    Page<CategoryEntity> findAllByPage(Pageable pageable);

    CategoryTree getCategoryTree();
}

