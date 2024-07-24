package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.PrincipalTypeRequest;
import com.enigmacamp.maneyself.model.dto.request.ProfileRequest;
import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.PrincipalTypeResponse;
import com.enigmacamp.maneyself.model.dto.response.ProfileResponse;
import com.enigmacamp.maneyself.service.PrincipalTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.PRINCIPAL_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class PrincipalTypeController {
    private final PrincipalTypeService principalTypeService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CommonResponse<PrincipalTypeResponse>> createPrincipal(@RequestBody PrincipalTypeRequest request, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        PrincipalTypeResponse response = principalTypeService.getOrSave(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        generateResponse(
                                "Principal type created successfully",
                                Optional.of(response)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<PrincipalTypeResponse>> getPrincipalById(@PathVariable String id, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        PrincipalTypeResponse response = principalTypeService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generateResponse(
                                "Principal found",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<PrincipalTypeResponse> generateResponse(String message, Optional<PrincipalTypeResponse> response) {
        return CommonResponse.<PrincipalTypeResponse>builder()
                .message(message)
                .data(response).build();
    }
}
