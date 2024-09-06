package com.shareduck.shareduck.domain.user.business.service.impl;

import com.shareduck.shareduck.common.exception.BusinessLogicException;
import com.shareduck.shareduck.domain.user.business.exception.UserExceptionCode;
import com.shareduck.shareduck.domain.user.business.service.UserGetService;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.enums.ProviderEnum;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {
    private final UserRepository repository;

    //TODO : Exceptuion 코드 수정
    @Override
    public void validIdExistAndJwt(String email) {
        repository.findByEmail(email)
            .ifPresent(
                user ->
                {
                    throw new BusinessLogicException(UserExceptionCode.USER_ALREADY_EXIST);
                }
            );
    }

    @Override
    public UserEntity validIdExistAndJwt(Long userId) {
        UserEntity entity = repository.findById(userId)
            .orElseThrow(() -> new BusinessLogicException(UserExceptionCode.USER_NOT_FOUND));
        validProviderJwt(entity);
        return entity;
    }

    @Override
    public Optional<UserEntity> getOptional(Long userId) {
        return repository.findById(userId);
    }

    @Override
    public UserEntity get(Long userId) {
        return getOptional(userId)
            .orElseThrow(
                () ->
                    new BusinessLogicException(UserExceptionCode.USER_NOT_FOUND)
            );
    }

    @Override
    public UserEntity get(String idx) {
        return repository.findByIdx(idx)
            .orElseThrow(
                () ->
                    new BusinessLogicException(UserExceptionCode.USER_NOT_FOUND)
            );
    }

    @Override
    public Page<UserEntity> get(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private void validProviderJwt(UserEntity entity){
        if(entity.getProvider() != ProviderEnum.JWT)
            throw new BusinessLogicException(UserExceptionCode.NOT_SUPPORT_PROVIDER);
    }
}

