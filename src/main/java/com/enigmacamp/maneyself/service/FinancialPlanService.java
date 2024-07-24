package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.request.FinancialPlanRequest;
import com.enigmacamp.maneyself.model.dto.response.FinancialPlanResponse;

public interface FinancialPlanService {
    FinancialPlanResponse createFinancialPlane(FinancialPlanRequest request, String userId);
}
