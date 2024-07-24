package com.enigmacamp.maneyself.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocationResponse {
    private String id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long needs;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long wish;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long saving;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long payment;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long emergency;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long invest;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long donate;
    private Date date;
}
