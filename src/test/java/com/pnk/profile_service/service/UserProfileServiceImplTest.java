package com.pnk.profile_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pnk.profile_service.dto.request.UserProfileCreationRequest;
import com.pnk.profile_service.dto.response.UserProfileResponse;
import com.pnk.profile_service.entity.UserProfile;
import com.pnk.profile_service.repository.UserProfileRepository;

@SpringBootTest
class UserProfileServiceImplTest {

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @MockBean
    private UserProfileRepository userProfileRepository;

    private UserProfileCreationRequest userProfileCreationRequest;

    private UserProfileResponse userProfileResponse;

    private UserProfile userProfile;

    private LocalDate dob;

    @BeforeEach
    void setUp() {
        dob = LocalDate.of(2000, 12, 31);

        userProfileCreationRequest = UserProfileCreationRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .address("Earth")
                .build();

        userProfileResponse = UserProfileResponse.builder()
                .id("aaf2922eb94f")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .address("Earth")
                .build();

        userProfile = UserProfile.builder()
                .id("aaf2922eb94f")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .address("Earth")
                .build();
    }

    @Test
    void testRegisterUserProfile_validRequest_success() {
        // GIVEN
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);
        // WHEN
        var response = userProfileService.registerUserProfile(userProfileCreationRequest);
        // THEN
        assertEquals("aaf2922eb94f", response.getId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("Doe", response.getLastName());
        assertEquals("2000-12-31", response.getDob().toString());
        assertEquals("Earth", response.getAddress());
    }

    @Test
    void testGetUserProfileById_valid_success() {
        // GIVEN
        when(userProfileRepository.findById("aaf2922eb94f")).thenReturn(Optional.of(userProfile));
        // WHEN
        var response = userProfileService.getUserProfileById("aaf2922eb94f");
        // THEN
        assertEquals("aaf2922eb94f", response.getId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("Doe", response.getLastName());
        assertEquals("2000-12-31", response.getDob().toString());
        assertEquals("Earth", response.getAddress());
    }

    @Test
    void testGetUserProfileById_invalid_throws_RuntimeException() {
        when(userProfileRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(null));

        assertThrows(RuntimeException.class, () -> userProfileService.getUserProfileById("aaf2922eb94f"));
    }
}
