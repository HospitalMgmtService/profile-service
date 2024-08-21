package com.pnk.profile_service.controller;

import com.pnk.profile_service.dto.response.UserProfileResponse;
import com.pnk.profile_service.service.UserProfileServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // injected by Constructor, no longer need of @Autowire
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileController {

    UserProfileServiceImpl userProfileService;


    @GetMapping("/{profileId}")
    UserProfileResponse getUserProfile(@PathVariable String profileId) {
        log.info(">> getUserProfile::profileId {}", profileId);

        return userProfileService.getUserProfileById(profileId);
    }


    @GetMapping()
    List<UserProfileResponse> getAllProfiles() {
        log.info(">> getAllProfiles");

        return userProfileService.getAllUserProfiles();
    }

}
