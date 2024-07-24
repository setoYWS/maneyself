package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.request.IncomeRequest;
import com.enigmacamp.maneyself.model.dto.response.IncomeResponse;

public interface IncomeService {
    IncomeResponse createIncome(IncomeRequest request, String userId);
}
