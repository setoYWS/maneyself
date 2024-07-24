package com.enigmacamp.maneyself.service.impl;

import com.enigmacamp.maneyself.model.dto.request.ProfileRequest;
import com.enigmacamp.maneyself.model.dto.response.ProfileResponse;
import com.enigmacamp.maneyself.model.entity.Profile;
import com.enigmacamp.maneyself.model.entity.User;
import com.enigmacamp.maneyself.repository.ProfileRepository;
import com.enigmacamp.maneyself.repository.UserRepository;
import com.enigmacamp.maneyself.service.ProfileService;
import com.enigmacamp.maneyself.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileResponse createProfile(ProfileRequest request, String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Profile profile = Profile.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .user(user)
                .build();
        profile = profileRepository.saveAndFlush(profile);
        return ProfileResponse.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .age(profile.getAge())
                .lastName(profile.getLastName())
                .build();
    }

    @Override
    public ProfileResponse getProfile(String userId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return convertToResponse(profile);
    }

    private ProfileResponse convertToResponse(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .age(profile.getAge())
                .build();
    }
}
