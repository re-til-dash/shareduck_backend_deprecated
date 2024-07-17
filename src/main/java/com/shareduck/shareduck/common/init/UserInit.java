package com.shareduck.shareduck.common.init;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.shareduck.shareduck.domain.user.entity.UserEntity;
import com.shareduck.shareduck.domain.user.entity.enums.UserRole;
import com.shareduck.shareduck.domain.user.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserInit {
	private final UserRepository repository;
	private static final String MAIL = "test001@gmail.com";

	@PostConstruct
	public void initUser() {
		Optional<UserEntity> findUser = repository.findById(1L);

		UserEntity userEntity = findUser.orElse(createEntity());
		repository.save(userEntity);
	}

	private UserEntity createEntity() {

		return UserEntity.builder()
			.email(MAIL)
			.role(UserRole.USER)
			.password("{noop}test001@@")
			.build();
	}

}
