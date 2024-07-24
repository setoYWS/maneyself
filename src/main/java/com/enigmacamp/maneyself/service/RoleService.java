package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.entity.Role;

public interface RoleService {
    Role getOrSave(Role.ERole role);
}
