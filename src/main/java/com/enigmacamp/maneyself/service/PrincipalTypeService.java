package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.request.PrincipalTypeRequest;
import com.enigmacamp.maneyself.model.dto.response.PrincipalTypeResponse;
import com.enigmacamp.maneyself.model.entity.PrincipalType;

public interface PrincipalTypeService {
    PrincipalTypeResponse getOrSave(PrincipalTypeRequest principal);
    PrincipalTypeResponse getById(String id);
}
