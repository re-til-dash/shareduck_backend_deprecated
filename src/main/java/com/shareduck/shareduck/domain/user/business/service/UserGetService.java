package com.shareduck.shareduck.domain.user.business.service;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import java.util.Optional;

public interface UserGetService {

    void validEntityExist(String email);

    UserEntity validEntityExist(Long userId);

    Optional<UserEntity> getOptional(Long userId);
}
