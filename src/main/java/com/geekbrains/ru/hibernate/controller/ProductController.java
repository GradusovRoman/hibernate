package com.geekbrains.ru.hibernate.controller;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import com.geekbrains.ru.hibernate.repository.ProductRepository;
import com.geekbrains.ru.hibernate.service.CategoryService;
import com.geekbrains.ru.hibernate.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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
    public String getProducts(@RequestParam(required = false) Integer pageNum,
                              @RequestParam(value = "category", required = false) String categoryAlias,
                              Model model) {

        final int pageSize = 5;

        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<ProductEntity> page = productService.findAllByPageAndCategory(pageRequest, categoryAlias);

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


    //    http://localhost:8090/product/list/greater?minPrice=120
    @GetMapping("/list/greater")
    public String getProductsByMinPrice(@RequestParam(name = "minPrice", required = false) Double minPrice,
                                        @RequestParam(value = "category", required = false) String categoryAlias,
                                        @RequestParam(required = false) Integer pageNum, Model model,
                                        @ModelAttribute(value = "violations") String violations) {
        final int pageSize = 5;
        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<ProductEntity> page = productRepository.findByPriceGreaterThan(pageRequest, minPrice);
        model.addAttribute("page", page);
        model.addAttribute("categoryTree", categoryService.getCategoryTree());
        return "product/list";
    }


    //    http://localhost:8090/product/list/lower?maxPrice=200
    @GetMapping("/list/lower")
    public String getProductsByMaxPrice(@RequestParam(name = "maxPrice", required = false) Double maxPrice,
                                        @RequestParam(value = "category", required = false) String categoryAlias,
                                        @RequestParam(required = false) Integer pageNum, Model model,
                                        @ModelAttribute(value = "violations") String violations) {
        final int pageSize = 5;

        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<ProductEntity> page = productRepository.findByPriceLessThan(pageRequest, maxPrice);
        model.addAttribute("page", page);
        model.addAttribute("categoryTree", categoryService.getCategoryTree());
        return "product/list";
    }

    //    http://localhost:8090/product/list/between?minPrice=130&maxPrice=900
    @GetMapping("/list/between")
    public String getProductsByPriceBetween(@RequestParam(name = "minPrice") Double minPrice,
                                            @RequestParam(name = "maxPrice") Double maxPrice,
                                            @RequestParam(value = "category", required = false) String categoryAlias,
                                            @RequestParam(required = false) Integer pageNum, Model model) {
        final int pageSize = 5;

        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<ProductEntity> page = productRepository.findByPriceBetween(pageRequest, minPrice, maxPrice);

        model.addAttribute("page", page);
        model.addAttribute("categoryTree", categoryService.getCategoryTree());
        return "product/list";
    }

    //    http://localhost:8090/product/list/findByName?title=Spider-man
    @GetMapping("/list/findByName")
    public String getProductsByName(@RequestParam(name = "title") String title,

                                    @RequestParam(value = "category", required = false) String categoryAlias,
                                    @RequestParam(required = false) Integer pageNum, Model model) {
        final int pageSize = 5;

        Pageable pageRequest = PageRequest.of(pageNum == null ? 0 : pageNum, pageSize);
        Page<ProductEntity> page = productRepository.findByTitleLike(pageRequest, title);
        model.addAttribute("page", page);
        model.addAttribute("categoryTree", categoryService.getCategoryTree());
        return "product/list";
    }


}