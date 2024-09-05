package com.shareduck.shareduck.common.security.service;

import com.shareduck.shareduck.common.security.vo.Principal;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity findUser =
                repository
                        .findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new Principal(findUser);
    }
}