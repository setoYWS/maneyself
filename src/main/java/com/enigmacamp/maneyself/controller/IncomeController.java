package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.IncomeRequest;
import com.enigmacamp.maneyself.model.dto.request.ManagementTypeRequest;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.IncomeResponse;
import com.enigmacamp.maneyself.model.dto.response.ManagementTypeResponse;
import com.enigmacamp.maneyself.service.IncomeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private CommonResponse<IncomeResponse> generateResponse(String message, Optional<IncomeResponse> response) {
        return CommonResponse.<IncomeResponse>builder()
                .message(message)
                .data(response).build();
    }
}
