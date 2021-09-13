package com.geekbrains.ru.hibernate.service;

import com.geekbrains.ru.hibernate.domain.RoleEntity;

public interface RoleService {
    RoleEntity findByName(String name);

}
