package com.shareduck.shareduck.domain.user.web.dto.request;

import jakarta.validation.constraints.Email;

public record PostUserRequest(
    @Email(message = " check the email format.")
    String email,
    //TODO : Password Validation
    String password,
    String profile
) {

}
