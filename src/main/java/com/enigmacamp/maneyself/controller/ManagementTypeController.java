package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.ManagementTypeRequest;
import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.ManagementTypeResponse;
import com.enigmacamp.maneyself.service.ManagementTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.MANAGEMENT_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class ManagementTypeController {
    private final ManagementTypeService managementTypeService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CommonResponse<ManagementTypeResponse>> createManagement(@RequestBody ManagementTypeRequest request, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        ManagementTypeResponse response = managementTypeService.getOrSave(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        generateResponse(
                                "Management Type created successfully",
                                Optional.of(response)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ManagementTypeResponse>> getManagementById(@PathVariable String id, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        ManagementTypeResponse response = managementTypeService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generateResponse(
                                "Management type found",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<ManagementTypeResponse> generateResponse(String message, Optional<ManagementTypeResponse> response) {
        return CommonResponse.<ManagementTypeResponse>builder()
                .message(message)
                .data(response).build();
    }
}
