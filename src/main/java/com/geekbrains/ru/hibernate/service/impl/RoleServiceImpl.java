package com.geekbrains.ru.hibernate.service.impl;

import com.geekbrains.ru.hibernate.domain.RoleEntity;
import com.geekbrains.ru.hibernate.repository.RoleRepository;
import com.geekbrains.ru.hibernate.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class RoleServiceImpl  implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity findByName(String name) {
        return roleRepository.findByNameIgnoreCase(name)
                .orElseThrow(EntityNotFoundException::new);
    }

}
