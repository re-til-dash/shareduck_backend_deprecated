package com.shareduck.shareduck.domain.user.web.dto.response;

import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostUserResponse(Long userId, String email, String nickname, String profile,
                               UserRole role, String phone, LocalDateTime createdAt,
                               LocalDateTime lastConnect) {

}