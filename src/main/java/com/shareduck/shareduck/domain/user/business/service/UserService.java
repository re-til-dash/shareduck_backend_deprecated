package com.shareduck.shareduck.domain.user.business.service;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;

public interface UserService {
    UserEntity post(UserEntity entity);
}
