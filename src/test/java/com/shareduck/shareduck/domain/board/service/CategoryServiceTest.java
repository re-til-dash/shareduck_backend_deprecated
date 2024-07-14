package com.shareduck.shareduck.domain.board.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shareduck.shareduck.domain.board.repository.CategoryRepository;
import com.shareduck.shareduck.domain.board.request.CategoryReq;
import com.shareduck.shareduck.domain.board.response.CategoryRes;
import com.shareduck.shareduck.domain.user.entity.UserEntity;
import com.shareduck.shareduck.domain.user.repository.UserRepository;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	void clean() {
		categoryRepository.deleteAll();
	}

	UserEntity getTestUser() {
		return userRepository.findById(1L)
			.get();
	}

	@Test
	@DisplayName("카테고리 생성")
	void test1() {
		UserEntity testUser = getTestUser();
		String categoryName = "새로운캬테고리";
		CategoryReq categoryReq = CategoryReq.TestBuilder()
			.name(categoryName)
			.properties(Map.of("key", "value", "key2", "value2"))
			.build();
		CategoryRes categoryRes = categoryService.createCategory(testUser.getId(), categoryReq);
		assertThat(categoryRes.getName()).isEqualTo(categoryName);
		assertThat(categoryRes.getProperties())
			.containsEntry("key", "value")
			.containsEntry("key2", "value2");
	}

	@Test
	@DisplayName("카테고리 업데이트")
	void test2() {
		UserEntity testUser = getTestUser();
		String categoryName = "새로운캬테고리";
		CategoryReq categoryReq = CategoryReq.TestBuilder()
			.name(categoryName)
			.properties(Map.of("key", "value", "key2", "value2"))
			.build();
		CategoryRes categoryRes = categoryService.createCategory(testUser.getId(), categoryReq);

		String updatedCategoryName = "업데이트된카테고리";
		CategoryReq updatedCategoryReq = CategoryReq.TestBuilder()
			.name(updatedCategoryName)
			.properties(Map.of("updateKey", 1, "updatedKey2", "updatedValue2"))
			.build();
		CategoryRes updatedCategoryRes = categoryService.updateCategory(testUser.getId(), categoryRes.getId(),
			updatedCategoryReq);

		assertThat(updatedCategoryRes.getName()).isEqualTo(updatedCategoryName);
		assertThat(updatedCategoryRes.getProperties())
			.containsEntry("updateKey", 1)
			.containsEntry("updatedKey2", "updatedValue2");
	}

	@Test
	@DisplayName("다른 유저의 카테고리리를 업데이트 하려는 경우 업데이트 실패")
	void test3() {
		UserEntity testUser = getTestUser();
		String categoryName = "새로운캬테고리";
		CategoryReq categoryReq = CategoryReq.TestBuilder()
			.name(categoryName)
			.properties(Map.of("key", "value", "key2", "value2"))
			.build();
		CategoryRes categoryRes = categoryService.createCategory(testUser.getId(), categoryReq);

		String updatedCategoryName = "업데이트된카테고리";
		CategoryReq updatedCategoryReq = CategoryReq.TestBuilder()
			.name(updatedCategoryName)
			.properties(Map.of("updateKey", 1, "updatedKey2", "updatedValue2"))
			.build();
		assertThatThrownBy(() -> categoryService.updateCategory(2L, categoryRes.getId(), updatedCategoryReq))
			.isInstanceOf(RuntimeException.class);
	}

	@Test
	@DisplayName("유저의 카테고리 조회")
	void test4() {
		UserEntity testUser = getTestUser();
		for (int i = 0; i < 5; i++) {
			CategoryReq categoryReq = CategoryReq.TestBuilder()
				.name("카테고리" + i)
				.properties(Map.of("key", "value", "key2", "value2"))
				.build();
			categoryService.createCategory(testUser.getId(), categoryReq);
		}
		List<CategoryRes> categoryRes = categoryService.listUserCategories(testUser.getId());
		assertThat(categoryRes.size()).isEqualTo(5);
		for (int i = 0; i < 5; i++) {
			assertThat(categoryRes.get(i).getName()).isEqualTo("카테고리" + i);
		}

	}

}