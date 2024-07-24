package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.request.ManagementTypeRequest;
import com.enigmacamp.maneyself.model.dto.response.ManagementTypeResponse;

public interface ManagementTypeService {
    ManagementTypeResponse getOrSave(ManagementTypeRequest request);
    ManagementTypeResponse getById(String id);
}
