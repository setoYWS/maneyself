package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.IncomeRequest;
import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.IncomeResponse;
import com.enigmacamp.maneyself.service.IncomeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.INCOME_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class IncomeController {
    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<CommonResponse<IncomeResponse>> createIncome(@RequestBody IncomeRequest request, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        IncomeResponse response = incomeService.createIncome(request, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        generateResponse(
                                "Income created successfully",
                                Optional.of(response)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<IncomeResponse>> getIncomeById(@PathVariable String id, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        IncomeResponse response = incomeService.getIncome(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generateResponse(
                                "Income found",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<IncomeResponse> generateResponse(String message, Optional<IncomeResponse> response) {
        return CommonResponse.<IncomeResponse>builder()
                .message(message)
                .data(response).build();
    }
}
