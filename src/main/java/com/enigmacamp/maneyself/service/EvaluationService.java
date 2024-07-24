package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.request.EvaluationRequest;
import com.enigmacamp.maneyself.model.dto.response.EvaluationResponse;

public interface EvaluationService {
    EvaluationResponse createEvaluation(EvaluationRequest request, String userId);
    EvaluationResponse getEvaluation(String id);
}
