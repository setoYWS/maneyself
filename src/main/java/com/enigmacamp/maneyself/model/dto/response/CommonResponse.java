package com.enigmacamp.maneyself.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponse <T>{
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Optional<T> data;
}
