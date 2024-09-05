package com.shareduck.shareduck.domain.user.persistence.repository;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
