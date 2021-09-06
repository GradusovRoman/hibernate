package com.geekbrains.ru.hibernate.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ProductSearchCondition {
    private String sortField = "title";
    private Sort.Direction sortDirection= Sort.Direction.ASC;

    private int pageNum;
    private Integer pageSize = 10;
}
