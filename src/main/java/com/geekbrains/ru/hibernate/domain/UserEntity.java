package com.geekbrains.ru.hibernate.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@ToString(exclude = "roleEntities")
@EqualsAndHashCode(exclude = {"id", "roleEntities"})
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotBlank(message = "Имя пользователя обязательно!")
    private String username;

    @NotNull(message = "Email обязателен")
    @Column(name = "email", length = 50, unique = true)
    private String email;

    @NotNull(message = "Пароль обязателен")
    @Column(name = "password", length = 100)
    private String password;

    private boolean enabled = true;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<RoleEntity> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username +
                ", email=" + email +
                '}';
    }

}
