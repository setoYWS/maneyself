package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.request.IncomeRequest;
import com.enigmacamp.maneyself.model.dto.response.IncomeResponse;
import com.enigmacamp.maneyself.model.entity.FinancialPlan;
import com.enigmacamp.maneyself.model.entity.Income;
import com.enigmacamp.maneyself.model.entity.User;
import com.enigmacamp.maneyself.repository.FinancialPlanRepository;
import com.enigmacamp.maneyself.repository.IncomeRepository;
import com.enigmacamp.maneyself.repository.UserRepository;
import com.enigmacamp.maneyself.service.AllocationService;
import com.enigmacamp.maneyself.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final FinancialPlanRepository financialPlanRepository;
    private final AllocationService allocationService;

    @Override
    public IncomeResponse createIncome(IncomeRequest request, String userId) {
        User user = userRepository.getUserById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        Income income = Income.builder()
                .title(request.getTitle())
                .nominal(request.getNominal())
                .transactionDate(request.getTransactionDate())
                .user(user)
                .build();
        income = incomeRepository.saveAndFlush(income);
        FinancialPlan financialPlan = financialPlanRepository.findFinancialPlanByUserId(userId);
        allocationService.createAllocation(income.getNominal(), financialPlan.getId());
        return IncomeResponse.builder()
                .id(income.getId())
                .title(income.getTitle())
                .nominal(income.getNominal())
                .transactionDate(income.getTransactionDate())
                .build();
    }

    @Override
    public IncomeResponse getIncome(String id) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Income not found!"));
        return convertToResponse(income);
    }

    IncomeResponse convertToResponse(Income income) {
        return IncomeResponse.builder()
                .id(income.getId())
                .title(income.getTitle())
                .nominal(income.getNominal())
                .transactionDate(income.getTransactionDate())
                .build();
    }
}
