package com.pnk.profile_service.service;

import com.pnk.profile_service.dto.request.UserProfileCreationRequest;
import com.pnk.profile_service.dto.response.UserProfileResponse;

import java.util.List;


public interface UserProfileService {

    UserProfileResponse registerUserProfile(UserProfileCreationRequest userProfileCreationRequest);

    List<UserProfileResponse> getAllUserProfiles();

    UserProfileResponse getUserProfileById(String id);
}
