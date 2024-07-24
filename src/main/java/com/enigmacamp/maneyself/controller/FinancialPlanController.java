package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.FinancialPlanRequest;
import com.enigmacamp.maneyself.model.dto.request.ManagementTypeRequest;
import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.FinancialPlanResponse;
import com.enigmacamp.maneyself.model.dto.response.ManagementTypeResponse;
import com.enigmacamp.maneyself.service.FinancialPlanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.FINANCIAL_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class FinancialPlanController {
    private final FinancialPlanService financialPlanService;

    @PostMapping
    public ResponseEntity<CommonResponse<FinancialPlanResponse>> createFinancialPlan(@RequestBody FinancialPlanRequest request, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        FinancialPlanResponse response = financialPlanService.createFinancialPlane(request, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        generateResponse(
                                "Financial plan created successfully",
                                Optional.of(response)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<FinancialPlanResponse>> getFinancialPlanById(@PathVariable String id, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        FinancialPlanResponse response = financialPlanService.getFinancialPlan(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generateResponse(
                                "Financial Plan found",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<FinancialPlanResponse> generateResponse(String message, Optional<FinancialPlanResponse> response) {
        return CommonResponse.<FinancialPlanResponse>builder()
                .message(message)
                .data(response).build();
    }
}
