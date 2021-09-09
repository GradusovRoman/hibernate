package com.geekbrains.ru.hibernate.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull(message = "Имя обязателено")
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @NotNull(message = "Email обязателен")
    @Column(name = "email", length = 50, unique = true)
    private String email;

    @NotNull(message = "Пароль обязателен")
    @Column(name = "password", length = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", lastName=" + lastName +
                ", email=" + email +
                '}';
    }

}
