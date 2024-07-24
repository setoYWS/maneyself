package com.enigmacamp.maneyself.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationResponse {
    private String id;
    private Long totalIncome;
    private Long totalExpense;
    private String status;
    private Date date;
}
