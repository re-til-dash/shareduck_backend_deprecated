package com.shareduck.shareduck.domain.user.web.dto.response;

import com.shareduck.shareduck.domain.user.persistence.enums.ProviderEnum;
import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import com.shareduck.shareduck.domain.user.persistence.enums.UserState;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetUserResponse(Long userId, String email, String nickname, String profile,
                              UserRole role, String phone, LocalDateTime createdAt, ProviderEnum provider,
                              String idx, UserState state, String name, LocalDateTime lastConnect) {

}