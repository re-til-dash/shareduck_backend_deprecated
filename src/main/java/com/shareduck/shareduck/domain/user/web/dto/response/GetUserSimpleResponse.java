package com.shareduck.shareduck.domain.user.web.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetUserSimpleResponse(String idx, String nickname, String email, String name, String profile,
                                    LocalDateTime lastConnect,LocalDateTime created, LocalDateTime updated) {

}
