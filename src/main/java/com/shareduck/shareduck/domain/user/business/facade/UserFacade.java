package com.shareduck.shareduck.domain.user.business.facade;

import com.shareduck.shareduck.domain.user.business.mapper.UserMapper;
import com.shareduck.shareduck.domain.user.business.service.UserGetService;
import com.shareduck.shareduck.domain.user.business.service.UserService;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.web.dto.request.PatchUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.request.PostUserRequest;
import com.shareduck.shareduck.domain.user.web.dto.response.PostUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserFacade {

    private final UserMapper mapper;
    private final UserGetService userGetService;
    private final UserService userService;

    public PostUserResponse post(PostUserRequest dto) {
        userGetService.validIdExistAndJwt(dto.email());
        UserEntity result = userService.post(mapper.toEntity(dto));
        log.info("join user count = {}", result.getId());
        return mapper.toDto(result);
    }

    public void delete(Long userId) {
        userGetService.getOptional(userId)
            .ifPresent(userService::delete);
    }

    public PostUserResponse patch(PatchUserRequest dto, Long userId) {
        UserEntity request = mapper.toEntity(dto, userId);
        UserEntity saved = userGetService.validIdExistAndJwt(userId);
        UserEntity result = userService.patch(saved, request);
        return mapper.toDto(result);
    }

    public void changePassword(Long userId, String password) {
        UserEntity entity = userGetService.validIdExistAndJwt(userId);
        userService.changePassword(entity, password);
    }
}
