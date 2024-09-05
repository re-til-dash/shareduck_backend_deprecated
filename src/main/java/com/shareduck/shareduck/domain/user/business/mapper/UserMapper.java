package com.shareduck.shareduck.domain.user.business.mapper;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.enums.ProviderEnum;
import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import com.shareduck.shareduck.domain.user.persistence.enums.UserState;
import com.shareduck.shareduck.domain.user.web.dto.request.PatchUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.GetUserResponse;
import com.shareduck.shareduck.domain.user.web.dto.response.UserIdResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //TODO : UUID 정책 수립 후 추후 변경 예정
    public UserEntity toEntity(PostUserRequest dto) {
        return UserEntity.builder()
            .email(dto.email())
            .password(dto.password())
            .nickname(UUID.randomUUID().toString())
            .name(dto.name())
            .profile(dto.profile())
            .state(UserState.ACTIVE)
            .lastConnect(LocalDateTime.now())
            .idx(UUID.randomUUID().toString())
            .provider(ProviderEnum.JWT)
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

    public UserIdResponse toPostDto(UserEntity userEntity) {
        return new UserIdResponse(userEntity.getIdx());
    }

    public GetUserResponse toGetDto(UserEntity entity) {
        return GetUserResponse.builder()
            .userId(entity.getId())
            .email(entity.getEmail())
            .nickname(entity.getNickname())
            .profile(entity.getProfile())
            .phone(entity.getPhone())
            .created(entity.getCreated())
            .updated(entity.getUpdated())
            .lastConnect(entity.getLastConnect())
            .role(entity.getRole())
            .provider(entity.getProvider())
            .idx(entity.getIdx())
            .build();
    }
}