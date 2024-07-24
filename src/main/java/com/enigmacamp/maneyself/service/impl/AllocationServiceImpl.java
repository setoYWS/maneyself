package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;
import com.enigmacamp.maneyself.model.entity.Allocation;
import com.enigmacamp.maneyself.model.entity.FinancialPlan;
import com.enigmacamp.maneyself.repository.AllocationRepository;
import com.enigmacamp.maneyself.repository.FinancialPlanRepository;
import com.enigmacamp.maneyself.service.AllocationService;
import com.enigmacamp.maneyself.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AllocationServiceImpl implements AllocationService {
    private final AllocationRepository allocationRepository;
    private final FinancialPlanRepository financialPlanRepository;

    @Override
    public void createAllocation(Long income, String financialId) {
        FinancialPlan financialPlan = financialPlanRepository.findById(financialId).orElseThrow(()->new ResourceNotFoundException("Financial Plan not found!"));
        Allocation allocation;
        if (financialPlan.getManagementType().getType().name().equals("40/10/10/30/10")) {
            allocation = Allocation.builder()
                    .needs(income * 40 / 100)
                    .payment(income * 10 / 100)
                    .saving(income * 10 / 100)
                    .emergency(income * 30 / 100)
                    .invest(income * 10 / 100)
                    .financialPlan(financialPlan)
                    .transactionDate(Date.from(Instant.now()))
                    .build();
        } else {
            allocation = Allocation.builder()
                    .needs(income * 50 / 100)
                    .wish(income * 30 / 100)
                    .saving(income * 20 / 100)
                    .financialPlan(financialPlan)
                    .transactionDate(Date.from(Instant.now()))
                    .build();
        }
        allocationRepository.saveAndFlush(allocation);
    }

    @Override
    public AllocationResponse getAllocationById(String id) {
        Allocation allocation = allocationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Allocation not found"));
        return AllocationResponse.builder()
                .needs(allocation.getNeeds())
                .id(allocation.getId())
                .date(allocation.getTransactionDate())
                .donate(allocation.getDonate())
                .emergency(allocation.getEmergency())
                .invest(allocation.getInvest())
                .payment(allocation.getPayment())
                .saving(allocation.getSaving())
                .wish(allocation.getWish())
                .build();
    }

    @Override
    public void subtractAllocation(Long amount, String financialId, String principalType) {
        Allocation allocation = allocationRepository.findAllocationByFinancialPlan(financialId);
        switch (principalType.toUpperCase()){
            case "NEEDS":
                allocation.setNeeds(allocation.getNeeds() - amount);
                break;
            case "DONATE":
                allocation.setDonate(allocation.getDonate() - amount);
                break;
            case "EMERGENCY":
                allocation.setEmergency(allocation.getEmergency() - amount);
                break;
            case "INVEST":
                allocation.setInvest(allocation.getInvest() - amount);
                break;
            case "PAYMENT":
                allocation.setPayment(allocation.getPayment() - amount);
                break;
            case "SAVING":
                allocation.setSaving(allocation.getSaving() - amount);
                break;
            case "WISH":
                allocation.setWish(allocation.getWish() - amount);
                break;
        }
        allocationRepository.saveAndFlush(allocation);
    }
}
