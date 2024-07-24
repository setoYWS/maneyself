package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.request.PrincipalTypeRequest;
import com.enigmacamp.maneyself.model.dto.response.PrincipalTypeResponse;
import com.enigmacamp.maneyself.model.entity.PrincipalType;
import com.enigmacamp.maneyself.repository.PrincipalTypeRepository;
import com.enigmacamp.maneyself.service.PrincipalTypeService;
import com.enigmacamp.maneyself.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalTypeServiceImpl implements PrincipalTypeService {
    private final PrincipalTypeRepository principalTypeRepository;

    @Override
    public PrincipalTypeResponse getOrSave(PrincipalTypeRequest request) {
        Optional<PrincipalType> principalType = principalTypeRepository.getPrincipalTypeByPrincipal(request.getPrincipalType());
        if(principalType.isPresent()){
            return PrincipalTypeResponse.builder()
                    .id(principalType.get().getId())
                    .principalType(principalType.get().getPrincipal().name())
                    .build();
        }

        PrincipalType currentPrincipalType = PrincipalType.builder()
                .principal(PrincipalType.Principal.valueOf(request.getPrincipalType()))
                .build();
        currentPrincipalType = principalTypeRepository.saveAndFlush(currentPrincipalType);
        return PrincipalTypeResponse.builder()
                .id(currentPrincipalType.getId())
                .principalType(currentPrincipalType.getPrincipal().name())
                .build();
    }

    @Override
    public PrincipalTypeResponse getById(String id) {
        PrincipalType principalType = principalTypeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Principal type not found"));
        return convertToResponse(principalType);
    }

    private PrincipalTypeResponse convertToResponse(PrincipalType principalType) {
        return PrincipalTypeResponse.builder()
                .id(principalType.getId())
                .principalType(principalType.getPrincipal().name())
                .build();
    }
}
