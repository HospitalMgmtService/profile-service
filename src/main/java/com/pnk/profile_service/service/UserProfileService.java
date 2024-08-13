package com.pnk.profile_service.service;

import com.pnk.profile_service.dto.request.UserProfileCreationRequest;
import com.pnk.profile_service.dto.response.UserProfileResponse;


public interface UserProfileService {

    UserProfileResponse registerUserProfile(UserProfileCreationRequest userProfileCreationRequest);

    UserProfileResponse getUserProfileById(String id);
}
