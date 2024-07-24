package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.EvaluationRequest;
import com.enigmacamp.maneyself.model.dto.request.ExpensesRequest;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.EvaluationResponse;
import com.enigmacamp.maneyself.model.dto.response.ExpensesResponse;
import com.enigmacamp.maneyself.service.EvaluationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.EVALUATION_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class EvaluationController {
    private EvaluationService evaluationService;

    @PostMapping
    public ResponseEntity<CommonResponse<EvaluationResponse>> createEvaluation(@RequestBody EvaluationRequest request, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        EvaluationResponse response = evaluationService.createEvaluation(request, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        generateResponse(
                                "Evaluation created successfully",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<EvaluationResponse> generateResponse(String message, Optional<EvaluationResponse> response) {
        return CommonResponse.<EvaluationResponse>builder()
                .message(message)
                .data(response).build();
    }
}
