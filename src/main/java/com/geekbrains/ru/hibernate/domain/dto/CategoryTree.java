package com.geekbrains.ru.hibernate.domain.dto;

import com.geekbrains.ru.hibernate.domain.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryTree {

    private List<TreeEntry> rootCategories;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreeEntry {
        private CategoryEntity category;
        private List<TreeEntry> subCategories;
    }
}