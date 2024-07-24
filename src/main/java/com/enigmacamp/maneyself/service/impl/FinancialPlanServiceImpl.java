package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.request.FinancialPlanRequest;
import com.enigmacamp.maneyself.model.dto.response.FinancialPlanResponse;
import com.enigmacamp.maneyself.model.dto.response.ManagementTypeResponse;
import com.enigmacamp.maneyself.model.dto.response.UserResponse;
import com.enigmacamp.maneyself.model.entity.FinancialPlan;
import com.enigmacamp.maneyself.model.entity.ManagementType;
import com.enigmacamp.maneyself.model.entity.User;
import com.enigmacamp.maneyself.repository.FinancialPlanRepository;
import com.enigmacamp.maneyself.repository.ManagementTypeRepository;
import com.enigmacamp.maneyself.repository.UserRepository;
import com.enigmacamp.maneyself.service.FinancialPlanService;
import com.enigmacamp.maneyself.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinancialPlanServiceImpl implements FinancialPlanService {
    private final FinancialPlanRepository financialPlanRepository;
    private final ManagementTypeRepository managementTypeRepository;
    private final UserRepository userRepository;

    @Override
    public FinancialPlanResponse createFinancialPlane(FinancialPlanRequest request, String userId) {
        ManagementType managementType = managementTypeRepository.findById(request.getManagementTypeId()).orElseThrow(() -> new ResourceNotFoundException("Management Type not found!"));
        User user = userRepository.getUserById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        FinancialPlan financialPlan = FinancialPlan.builder()
                .user(user)
                .managementType(managementType)
                .build();
        financialPlan = financialPlanRepository.saveAndFlush(financialPlan);
        return FinancialPlanResponse.builder()
                .id(financialPlan.getId())
                .user(UserResponse.builder()
                        .id(financialPlan.getUser().getId())
                        .email(financialPlan.getUser().getEmail())
                        .build())
                .managementType(ManagementTypeResponse.builder()
                        .id(managementType.getId())
                        .type(managementType.getType().name())
                        .build())
                .build();
    }

    @Override
    public FinancialPlanResponse getFinancialPlan(String id) {
        FinancialPlan financialPlan = financialPlanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Financial Plan not found!"));
        return convertToResponse(financialPlan);
    }

    FinancialPlanResponse convertToResponse(FinancialPlan financialPlan) {
        return FinancialPlanResponse.builder()
                .id(financialPlan.getId())
                .managementType(ManagementTypeResponse.builder()
                        .id(financialPlan.getManagementType().getId())
                        .type(financialPlan.getManagementType().getType().name())
                        .build())
                .user(UserResponse.builder()
                        .id(financialPlan.getUser().getId())
                        .email(financialPlan.getUser().getEmail())
                        .build())
                .build();
    }
}
