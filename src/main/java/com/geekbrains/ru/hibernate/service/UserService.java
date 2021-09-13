package com.geekbrains.ru.hibernate.service;

import com.geekbrains.ru.hibernate.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity findByUsername(String username);

    UserEntity save(UserEntity user);

    Page<UserEntity> findAllByPage(Pageable pageRequest);

    void setEnable(Long userId, Boolean enable);
}
