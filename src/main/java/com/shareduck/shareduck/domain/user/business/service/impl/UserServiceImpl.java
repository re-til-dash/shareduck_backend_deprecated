package com.shareduck.shareduck.domain.user.business.service.impl;

import com.shareduck.shareduck.domain.user.business.service.UserService;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserEntity post(UserEntity entity) {
        entity.addPassword(encoder.encode(entity.getPassword()));
        UserEntity save = repository.save(entity);
        log.debug("current post user count = {}", save.getId());
        return save;
    }

}
