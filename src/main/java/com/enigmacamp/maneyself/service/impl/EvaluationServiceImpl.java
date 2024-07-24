package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.request.EvaluationRequest;
import com.enigmacamp.maneyself.model.dto.response.EvaluationResponse;
import com.enigmacamp.maneyself.model.entity.Evaluation;
import com.enigmacamp.maneyself.model.entity.User;
import com.enigmacamp.maneyself.repository.EvaluationRepository;
import com.enigmacamp.maneyself.repository.ExpensesRepository;
import com.enigmacamp.maneyself.repository.IncomeRepository;
import com.enigmacamp.maneyself.repository.UserRepository;
import com.enigmacamp.maneyself.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {
    private final IncomeRepository incomeRepository;
    private final ExpensesRepository expensesRepository;
    private final UserRepository userRepository;
    private final EvaluationRepository evaluationRepository;

    @Override
    public EvaluationResponse createEvaluation(EvaluationRequest request, String userId) {
        User user = userRepository.getUserById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        LocalDate localDate = request.getTransactionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Integer month = localDate.getMonthValue();
        Integer year = localDate.getYear();
        Long totalIncome = incomeRepository.getTotalIncome(month,year);
        Long totalExpense = expensesRepository.getTotalExpense(month,year);
        Evaluation.Status status;
        Long condition = (totalIncome - totalExpense);
        Long good = totalIncome * 5/100;
        Long warn = totalIncome /100;
        if(condition > good){
            status = Evaluation.Status.SAFE;
        } else if (condition > warn) {
            status = Evaluation.Status.WARNING;
        } else {
            status = Evaluation.Status.DANGEROUS;
        }
        Evaluation evaluation = Evaluation.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .transactionDate(request.getTransactionDate())
                .user(user)
                .status(status)
                .build();
        evaluation = evaluationRepository.saveAndFlush(evaluation);

        return EvaluationResponse.builder()
                .id(evaluation.getId())
                .totalIncome(evaluation.getTotalIncome())
                .totalExpense(evaluation.getTotalExpense())
                .date(evaluation.getTransactionDate())
                .status(evaluation.getStatus().name())
                .build();
    }

    @Override
    public EvaluationResponse getEvaluation(String id) {
        Evaluation evaluation = evaluationRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Evaluation not found!"));
        return convertToResponse(evaluation);
    }

    EvaluationResponse convertToResponse(Evaluation evaluation) {
        return EvaluationResponse.builder()
                .id(evaluation.getId())
                .totalIncome(evaluation.getTotalIncome())
                .totalExpense(evaluation.getTotalExpense())
                .date(evaluation.getTransactionDate())
                .status(evaluation.getStatus().name())
                .build();
    }
}
