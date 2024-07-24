package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.ExpensesRequest;
import com.enigmacamp.maneyself.model.dto.request.IncomeRequest;
import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.ExpensesResponse;
import com.enigmacamp.maneyself.model.dto.response.IncomeResponse;
import com.enigmacamp.maneyself.service.ExpensesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.EXPENSE_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class ExpenseController {
    private ExpensesService expensesService;
    @PostMapping
    public ResponseEntity<CommonResponse<ExpensesResponse>> createExpense(@RequestBody ExpensesRequest request, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        ExpensesResponse response = expensesService.createExpense(request, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        generateResponse(
                                "Expense created successfully",
                                Optional.of(response)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ExpensesResponse>> getExpenseById(@PathVariable String id, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        ExpensesResponse response = expensesService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generateResponse(
                                "Expenses found",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<ExpensesResponse> generateResponse(String message, Optional<ExpensesResponse> response) {
        return CommonResponse.<ExpensesResponse>builder()
                .message(message)
                .data(response).build();
    }
}
