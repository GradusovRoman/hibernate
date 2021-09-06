package com.geekbrains.ru.hibernate.controller;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.domain.dto.ProductEntitySearchByPrice;
import com.geekbrains.ru.hibernate.repository.ProductRepository;
import com.geekbrains.ru.hibernate.service.CategoryService;
import com.geekbrains.ru.hibernate.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
//@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    private final Validator validator;

    @GetMapping("/list")

    public String getProducts(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                              @RequestParam(value = "minPrice", required = false) Double minPrice,
                              @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                              @RequestParam(value = "category", required = false) String categoryAlias,
                              @RequestParam(value = "title", required = false) String title,
                              Model model) {
        final int pageSize = 5;
        Page<ProductEntity> page;
        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = 0.0;

        if (minPrice == 0 && maxPrice == 0 && StringUtils.isBlank(title)) {
            page = productService.findAllByPageAndCategory(pageRequest, categoryAlias);
        } else if (!StringUtils.isBlank(title)) {
            page = productRepository.findByTitleIsLikeIgnoreCase(pageRequest, title);
        } else if (minPrice != 0.0 & maxPrice == 0.0) {

            page = productRepository.findByPriceGreaterThan(pageRequest, maxPrice);
        } else if (maxPrice != 0.0 & minPrice == 0.0) {

            page = productRepository.findByPriceLessThan(pageRequest, maxPrice);
        } else {
            page = productRepository.findByPriceBetween(pageRequest, minPrice, maxPrice);
        }
        model.addAttribute("priceRange", new ProductEntitySearchByPrice(0.0, 99000.0));
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("title", title);
        model.addAttribute("page", page);
        model.addAttribute("categoryTree", categoryService.getCategoryTree());


        return "product/list";
    }

    @GetMapping("/form")
    public String getProductForm(@RequestParam(required = false) Long id, Model model,
                                 @ModelAttribute(value = "violations") String violations) {

        if (id != null) {
            ProductEntity product = productService.findById(id);
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", new ProductEntity());
        }

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("violations", violations);

        return "product/form";
    }

    @PostMapping
    public RedirectView saveProduct(ProductEntity product,
                                    @RequestParam(required = false) MultipartFile image,
                                    RedirectAttributes attributes) {

        Set<ConstraintViolation<ProductEntity>> violationResult = validator.validate(product);
        if (CollectionUtils.isNotEmpty(violationResult)) {
            String violations = violationResult.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));

            attributes.addFlashAttribute("violations", violations);

            return new RedirectView("/product/form");
        }

        productService.saveWithImage(product, image);

        return new RedirectView("/product/list");
    }

    @GetMapping("/delete")
    public RedirectView deleteProductById(@RequestParam Long id) {
        productService.deleteById(id);

        return new RedirectView("/product/list");
    }


}