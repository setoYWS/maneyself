package com.enigmacamp.maneyself.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialPlanResponse {
    private String id;
    private UserResponse user;
    private ManagementTypeResponse managementType;
}
