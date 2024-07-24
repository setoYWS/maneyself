package com.enigmacamp.maneyself.model.dto.response;

import com.enigmacamp.maneyself.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String email;
    private Role.ERole role;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String token;
}
