package com.shareduck.shareduck.domain.user.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record PostUserRequest(
    @Email(message = " check the email format.")
    String email,
    @NotEmpty
    String name,
    //TODO : Password Validation
    String password,
    String profile
) {

}
