package com.enigmacamp.maneyself.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpensesRequest {
    private String title;
    private Long nominal;
    private String principalType;
    private Date transactionDate;
}
