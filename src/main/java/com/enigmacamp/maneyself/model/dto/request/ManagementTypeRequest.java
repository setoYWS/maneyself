package com.enigmacamp.maneyself.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagementTypeRequest {
    private String id;
    private String type;
    private List<PrincipalTypeRequest> principalTypeList;
}
