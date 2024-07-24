package com.enigmacamp.maneyself.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrincipalTypeResponse {
    private String id;
    private String principalType;
}
