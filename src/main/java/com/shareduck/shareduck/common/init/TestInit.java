package com.shareduck.shareduck.common.init;

import com.shareduck.shareduck.domain.board.entity.Category;
import com.shareduck.shareduck.domain.board.repository.CategoryRepository;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TestInit {

    private final UserRepository userRepository;

    private static final String MAIL = "test001@gmail.com";

    private final CategoryRepository categoryRepository;

    @Transactional
    @PostConstruct
    public void initAll() {
        UserEntity testUser = initUser();
        Category initCategory1 = categoryRepository.findById(1L)
                .orElse(categoryRepository.save(Category.create(testUser, "테스트카테고리1111", "카테고리링크", Map.of("key", "value"))));

        Category initCategory2 = categoryRepository.findById(2L)
                .orElse(categoryRepository.save(Category.create(testUser, "테스트카테고리2222", "카테고리링크", Map.of("key", "value"))));

    }

    private UserEntity initUser() {
        UserEntity user = userRepository.findById(1L)
                .orElse(userRepository.save(createEntity()));

        return user;
    }

    private UserEntity createEntity() {

        return UserEntity.builder()
                .email(MAIL)
                .role(UserRole.USER)
                .password("{noop}test001@@")
                .build();
    }

}
