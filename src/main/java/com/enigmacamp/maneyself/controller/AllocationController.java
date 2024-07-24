package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.FinancialPlanRequest;
import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.FinancialPlanResponse;
import com.enigmacamp.maneyself.service.AllocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.ALLOCATION_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class AllocationController {
    private AllocationService allocationService;
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<AllocationResponse>> getAllocationById(@PathVariable String id, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        AllocationResponse response = allocationService.getAllocationById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generateResponse(
                                "Allocation found",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<AllocationResponse> generateResponse(String message, Optional<AllocationResponse> response) {
        return CommonResponse.<AllocationResponse>builder()
                .message(message)
                .data(response).build();
    }
}
