package com.enigmacamp.maneyself.service;

import com.enigmacamp.maneyself.model.dto.request.ProfileRequest;
import com.enigmacamp.maneyself.model.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest request, String userId);
}
