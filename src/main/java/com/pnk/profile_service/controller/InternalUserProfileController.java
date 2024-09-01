package com.pnk.profile_service.controller;

import com.pnk.profile_service.dto.request.UserProfileCreationRequest;
import com.pnk.profile_service.dto.response.UserProfileResponse;
import com.pnk.profile_service.service.UserProfileServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor // injected by Constructor, no longer need of @Autowire
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InternalUserProfileController {

    UserProfileServiceImpl userProfileService;


    @PostMapping
    UserProfileResponse createUserProfile(@RequestBody UserProfileCreationRequest userProfileCreationRequest) {
        log.info(">> createUserProfile::userProfileCreationRequest {}", userProfileCreationRequest);

        return userProfileService.registerUserProfile(userProfileCreationRequest);
    }

}
