package com.geekbrains.ru.hibernate.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@ToString(exclude = {"products", "subCategories"})
@EqualsAndHashCode(exclude = {"id", "products", "subCategories"})
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя категории обязательно")
    private String name;

    @NotBlank(message = "Алиас категории обязателен")
    private String alias;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<CategoryEntity> subCategories;
}
