package com.shareduck.shareduck.domain.user.business.service;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserGetService {

    void validIdExistAndJwt(String email);

    UserEntity validIdExistAndJwt(Long userId);

    Optional<UserEntity> getOptional(Long userId);

    UserEntity get(Long userId);

    UserEntity get(String idx);

    Page<UserEntity> get(Pageable pageable);
}
