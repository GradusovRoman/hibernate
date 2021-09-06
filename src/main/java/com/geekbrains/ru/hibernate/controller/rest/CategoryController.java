package com.geekbrains.ru.hibernate.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.geekbrains.ru.hibernate.domain.constant.RequestNameConstant.*;

@RestController("restCategoryController")
@RequestMapping(API_V1 + CATEGORY)
public class CategoryController {
}
