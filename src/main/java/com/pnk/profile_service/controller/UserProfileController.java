package com.pnk.profile_service.controller;

import org.springframework.web.bind.annotation.*;

import com.pnk.profile_service.dto.request.UserProfileCreationRequest;
import com.pnk.profile_service.dto.response.UserProfileResponse;
import com.pnk.profile_service.service.UserProfileServiceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // injected by Constructor, no longer need of @Autowire
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileController {

    UserProfileServiceImpl userProfileService;

    @PostMapping
    UserProfileResponse createUserProfile(@RequestBody UserProfileCreationRequest userProfileCreationRequest) {
        return userProfileService.registerUserProfile(userProfileCreationRequest);
    }

    @GetMapping("/{profileId}")
    UserProfileResponse getUserProfile(@PathVariable String profileId) {
        return userProfileService.getUserProfileById(profileId);
    }
}
