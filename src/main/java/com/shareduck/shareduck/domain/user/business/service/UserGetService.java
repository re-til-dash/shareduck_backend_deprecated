package com.shareduck.shareduck.domain.user.business.service;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import java.util.Optional;

public interface UserGetService {

    void validIdExistAndJwt(String email);

    UserEntity validIdExistAndJwt(Long userId);

    Optional<UserEntity> getOptional(Long userId);

    UserEntity get(Long userId);

    UserEntity get(String idx);
}
