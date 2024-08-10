package com.shareduck.shareduck.domain.user.business.facade;

import com.shareduck.shareduck.domain.user.business.mapper.UserMapper;
import com.shareduck.shareduck.domain.user.business.service.UserGetService;
import com.shareduck.shareduck.domain.user.business.service.UserService;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
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
        userGetService.validEntityExist(dto.email());
        UserEntity result = userService.post(mapper.toEntity(dto));
        log.info("join user count = {}", result.getId());
        return mapper.toDto(result);
    }
}
