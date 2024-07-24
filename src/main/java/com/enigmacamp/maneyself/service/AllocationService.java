package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;

public interface AllocationService {
    void createAllocation(Long income, String financialId);
    AllocationResponse getAllocationById(String id);
    void subtractAllocation(Long amount, String financialId, String principalType);
}
