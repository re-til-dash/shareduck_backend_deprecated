package com.shareduck.shareduck.domain.user.business.service.impl;

import com.shareduck.shareduck.domain.user.business.service.UserGetService;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {
    private final UserRepository repository;

    @Override
    public void validEntityExist(String email) {
        repository.findByEmail(email)
            .ifPresent(
                user ->
                {
                    throw new RuntimeException("User with email " + email + " already exists");
                }
            );
    }
}
//TODO : Exceptuion 코드 수정
