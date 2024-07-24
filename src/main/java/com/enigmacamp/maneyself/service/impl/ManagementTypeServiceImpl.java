package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.request.ManagementTypeRequest;
import com.enigmacamp.maneyself.model.dto.request.PrincipalTypeRequest;
import com.enigmacamp.maneyself.model.dto.response.ManagementTypeResponse;
import com.enigmacamp.maneyself.model.dto.response.PrincipalTypeResponse;
import com.enigmacamp.maneyself.model.entity.ManagementType;
import com.enigmacamp.maneyself.model.entity.PrincipalType;
import com.enigmacamp.maneyself.repository.ManagementTypeRepository;
import com.enigmacamp.maneyself.service.ManagementTypeService;
import com.enigmacamp.maneyself.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagementTypeServiceImpl implements ManagementTypeService {
    private final ManagementTypeRepository managementTypeRepository;

    @Override
    public ManagementTypeResponse getOrSave(ManagementTypeRequest request) {
        Optional<ManagementType> managementType = managementTypeRepository.getManagementTypeByType(request.getType());
        if (managementType.isPresent()){
            return ManagementTypeResponse.builder()
                    .type(managementType.get().getType().name())
                    .id(managementType.get().getId())
                    .principalTypeList(managementType.get().getPrincipals().stream().map(this::convertToResponse).toList())
                    .build();
        }
        ManagementType currentManagementType = ManagementType.builder()
                .type(ManagementType.ManagementPlanType.valueOf(request.getType()))
                .principals(request.getPrincipalTypeList().stream().map(this::convertToType).toList())
                .build();
        currentManagementType = managementTypeRepository.saveAndFlush(currentManagementType);
        return ManagementTypeResponse.builder()
                .id(currentManagementType.getId())
                .type(currentManagementType.getType().name())
                .principalTypeList(currentManagementType.getPrincipals().stream().map(this::convertToResponse).toList())
                .build();
    }

    @Override
    public ManagementTypeResponse getById(String id) {
        ManagementType managementType = managementTypeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Management type with id " + id + " not found"));
        return convertToResponse(managementType);
    }

    ManagementTypeResponse convertToResponse(ManagementType managementType) {
        return ManagementTypeResponse.builder()
                .id(managementType.getId())
                .type(managementType.getType().name())
                .principalTypeList(managementType.getPrincipals().stream().map(this::convertToResponse).toList())
                .build();
    }

    PrincipalType convertToType(PrincipalTypeRequest request){
        return PrincipalType.builder()
                .id(request.getId())
                .principal(PrincipalType.Principal.valueOf(request.getPrincipalType()))
                .build();
    }

    PrincipalTypeResponse convertToResponse(PrincipalType request){
        return PrincipalTypeResponse.builder()
                .id(request.getId())
                .principalType(request.getPrincipal().name())
                .build();
    }
}
