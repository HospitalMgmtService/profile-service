package com.pnk.profile_service.service;

import com.pnk.profile_service.dto.request.UserProfileCreationRequest;
import com.pnk.profile_service.dto.response.UserProfileResponse;
import com.pnk.profile_service.entity.UserProfile;
import com.pnk.profile_service.mapper.UserProfileMapper;
import com.pnk.profile_service.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor // injected by Constructor, no longer need of @Autowire
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;


    @Override
    public UserProfileResponse registerUserProfile(UserProfileCreationRequest userProfileCreationRequest) {
        UserProfile userProfile = userProfileMapper.toUserProfile(userProfileCreationRequest);

        userProfile = userProfileRepository.save(userProfile);

        log.info(">> getUserProfileById::userProfile: {}", userProfile);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }


    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")       // authorize before the method called
    public List<UserProfileResponse> getAllUserProfiles() {
        log.info(">> getAllUserProfiles");

        var profiles = userProfileRepository.findAll();

        return profiles.stream().map(userProfileMapper::toUserProfileResponse).toList();
    }


    @Override
    public UserProfileResponse getUserProfileById(String id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User profile not found"));

        log.info(">> getUserProfileById::id {} >> userProfile: {}", id, userProfile);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
