package com.enigmacamp.maneyself.controller;

import com.enigmacamp.maneyself.constant.APIUrl;
import com.enigmacamp.maneyself.model.dto.request.ProfileRequest;
import com.enigmacamp.maneyself.model.dto.response.AllocationResponse;
import com.enigmacamp.maneyself.model.dto.response.CommonResponse;
import com.enigmacamp.maneyself.model.dto.response.ProfileResponse;
import com.enigmacamp.maneyself.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(APIUrl.PROFILE_API)
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<CommonResponse<ProfileResponse>> createProfile(@RequestBody ProfileRequest request, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        ProfileResponse response = profileService.createProfile(request,userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        generateResponse(
                                "Profile created successfully",
                                Optional.of(response)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ProfileResponse>> getProfileById(@PathVariable String id, HttpServletRequest reqServlet) {
        String userId = (String) reqServlet.getAttribute("userId");
        ProfileResponse response = profileService.getProfile(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        generateResponse(
                                "Profile found",
                                Optional.of(response)
                        )
                );
    }

    private CommonResponse<ProfileResponse> generateResponse(String message, Optional<ProfileResponse> response) {
        return CommonResponse.<ProfileResponse>builder()
                .message(message)
                .data(response).build();
    }
}
