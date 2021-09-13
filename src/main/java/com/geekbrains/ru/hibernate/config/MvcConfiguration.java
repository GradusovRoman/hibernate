package com.geekbrains.ru.hibernate.config;

import com.geekbrains.ru.hibernate.domain.ProductEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    private HashMap<Long, List<ProductEntity>> cartMap;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "data";
        String staticPath = Paths.get(System.getProperty("user.dir"), dirName).toFile().getAbsolutePath();

        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file://" + staticPath + "/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/**");
    }

    @Bean
    public HashMap<Long, List<ProductEntity>> cartsRepository() {
        if (cartMap == null)
            cartMap = new HashMap<>();
        return cartMap;
    }

    @Bean
    public String querySubSelect() {
        return "select c.id from category c";
    }
}

