package com.pnk.profile_service.dto.request;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileCreationRequest {

    String firstName;

    String lastName;

    LocalDate dob;

    String address;
}
