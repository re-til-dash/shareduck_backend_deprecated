package com.shareduck.shareduck.domain.user.web.dto.response;

import lombok.Builder;

@Builder
public record PostUserResponse(
    Long userId,
    String email,
    String nickname
) {

}
