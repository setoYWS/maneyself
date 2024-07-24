package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.request.ExpensesRequest;
import com.enigmacamp.maneyself.model.dto.response.ExpensesResponse;

public interface ExpensesService {
    ExpensesResponse createExpense(ExpensesRequest request, String userId);
}
