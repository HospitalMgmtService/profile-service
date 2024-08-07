package com.pnk.profile_service.mapper;

import com.pnk.profile_service.dto.request.UserProfileCreationRequest;
import com.pnk.profile_service.dto.response.UserProfileResponse;
import com.pnk.profile_service.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class UserProfileMapperImpl implements UserProfileMapper {

    @Override
    public UserProfile toUserProfile(UserProfileCreationRequest userProfileCreationRequest) {
        if ( userProfileCreationRequest == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.firstName( userProfileCreationRequest.getFirstName() );
        userProfile.lastName( userProfileCreationRequest.getLastName() );
        userProfile.dob( userProfileCreationRequest.getDob() );
        userProfile.address( userProfileCreationRequest.getAddress() );

        return userProfile.build();
    }

    @Override
    public UserProfileResponse toUserProfileResponse(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        UserProfileResponse.UserProfileResponseBuilder userProfileResponse = UserProfileResponse.builder();

        userProfileResponse.id( userProfile.getId() );
        userProfileResponse.firstName( userProfile.getFirstName() );
        userProfileResponse.lastName( userProfile.getLastName() );
        userProfileResponse.dob( userProfile.getDob() );
        userProfileResponse.address( userProfile.getAddress() );

        return userProfileResponse.build();
    }
}
