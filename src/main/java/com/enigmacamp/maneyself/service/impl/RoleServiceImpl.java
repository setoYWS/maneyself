package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.entity.Role;
import com.enigmacamp.maneyself.repository.RoleRepository;
import com.enigmacamp.maneyself.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(Role.ERole role) {
        Optional<Role> foundRole = roleRepository.getRoleByRole(role.name());
        if (foundRole.isPresent()){
            return foundRole.get();
        }
        Role currentRole = Role.builder()
                .role(role)
                .build();
        return roleRepository.saveAndFlush(currentRole);
    }
}
