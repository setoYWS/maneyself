package com.enigmacamp.maneyself.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagementTypeResponse {
    private String id;
    private String type;
    private List<PrincipalTypeResponse> principalTypeList;
}
