package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.request.ExpensesRequest;
import com.enigmacamp.maneyself.model.dto.response.ExpensesResponse;
import com.enigmacamp.maneyself.model.entity.Expenses;
import com.enigmacamp.maneyself.model.entity.FinancialPlan;
import com.enigmacamp.maneyself.model.entity.PrincipalType;
import com.enigmacamp.maneyself.model.entity.User;
import com.enigmacamp.maneyself.repository.ExpensesRepository;
import com.enigmacamp.maneyself.repository.FinancialPlanRepository;
import com.enigmacamp.maneyself.repository.PrincipalTypeRepository;
import com.enigmacamp.maneyself.repository.UserRepository;
import com.enigmacamp.maneyself.service.AllocationService;
import com.enigmacamp.maneyself.service.ExpensesService;
import com.enigmacamp.maneyself.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final FinancialPlanRepository financialPlanRepository;
    private final UserRepository userRepository;
    private final PrincipalTypeRepository principalTypeRepository;
    private final AllocationService allocationService;

    @Override
    public ExpensesResponse createExpense(ExpensesRequest request, String userId) {
        User user = userRepository.getUserById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        PrincipalType principalType = principalTypeRepository.getPrincipalTypeByPrincipal(request.getPrincipalType()).orElseThrow(()-> new ResourceNotFoundException("Not Found!"));
        FinancialPlan financialPlan = financialPlanRepository.findFinancialPlanByUserId(userId);
        Expenses expenses = Expenses.builder()
                .title(request.getTitle())
                .principal(principalType.getPrincipal())
                .nominal(request.getNominal())
                .user(user)
                .transactionDate(request.getTransactionDate())
                .build();
        expenses = expensesRepository.saveAndFlush(expenses);
        allocationService.subtractAllocation(request.getNominal(), financialPlan.getId(), principalType.getPrincipal().name());
        return ExpensesResponse.builder()
                .id(expenses.getId())
                .title(expenses.getTitle())
                .nominal(expenses.getNominal())
                .transactionDate(expenses.getTransactionDate())
                .build();
    }

    @Override
    public ExpensesResponse getById(String id) {
        Expenses expenses = expensesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found!"));
        return convertToExpensesResponse(expenses);
    }

    ExpensesResponse convertToExpensesResponse(Expenses expenses) {
        return ExpensesResponse.builder()
                .id(expenses.getId())
                .title(expenses.getTitle())
                .nominal(expenses.getNominal())
                .transactionDate(expenses.getTransactionDate())
                .build();
    }
}
