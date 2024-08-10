package com.shareduck.shareduck.domain.user.business.mapper;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import com.shareduck.shareduck.domain.user.persistence.enums.UserState;
import com.shareduck.shareduck.domain.user.web.dto.request.PatchUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.PatchUserResponse;
import com.shareduck.shareduck.domain.user.web.dto.response.PostUserResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(PostUserRequest dto) {
        return UserEntity.builder()
            .email(dto.email())
            .password(dto.password())
            .nickname(UUID.randomUUID().toString())
            .profile(dto.profile())
            .state(UserState.ACTIVE)
            .role(UserRole.USER)
            .build();
    }

    public UserEntity toEntity(PatchUserRequest dto, Long userId) {
        return UserEntity.builder()
            .id(userId)
            .email(dto.email())
            .nickname(dto.nickname())
            .profile(dto.profile())
            .build();
    }

    public PostUserResponse toDto(UserEntity entity) {
        return PostUserResponse.builder()
            .userId(entity.getId())
            .email(entity.getEmail())
            .nickname(entity.getNickname())
            .profile(entity.getProfile())
            .phone(entity.getPhone())
            .createdAt(entity.getCreated())
            .lastConnect(entity.getLastConnect())
            .build();
    }
}