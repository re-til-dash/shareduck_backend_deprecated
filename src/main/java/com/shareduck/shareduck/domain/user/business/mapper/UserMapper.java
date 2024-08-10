package com.shareduck.shareduck.domain.user.business.mapper;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import com.shareduck.shareduck.domain.user.persistence.enums.UserState;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.PostUserResponse;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(PostUserRequest dto) {
        return UserEntity.builder()
            .email(dto.email())
            .password(dto.password())
            .nickname(UUID.randomUUID().toString())
            .state(UserState.ACTIVE)
            .role(UserRole.USER)
            .build();
    }

    public PostUserResponse toDto(UserEntity entity) {
        return PostUserResponse.builder()
            .email(entity.getEmail())
            .nickname(entity.getNickname())
            .userId(entity.getId())
            .build();
    }
}
