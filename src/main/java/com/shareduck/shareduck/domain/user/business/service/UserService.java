package com.shareduck.shareduck.domain.user.business.service;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;

public interface UserService {
    UserEntity post(UserEntity entity);

    void delete(UserEntity entity);

    UserEntity patch(UserEntity saved, UserEntity request);
}
