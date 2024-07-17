package com.shareduck.shareduck.domain.board.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.shareduck.shareduck.domain.board.entity.Category;
import com.shareduck.shareduck.domain.board.repository.CategoryRepository;
import com.shareduck.shareduck.domain.board.request.CategoryReq;
import com.shareduck.shareduck.domain.user.entity.UserEntity;
import com.shareduck.shareduck.domain.user.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void init() {
		categoryRepository.deleteAll();
	}

	UserEntity getUser() {
		return userRepository.findById(1L).get();
	}

	@Test
	@DisplayName("카테고리 작성")
	void test1() throws Exception {
		String categoryName = "테스트카테고리임";
		Map<String, Object> properties = Map.of("키", "벨류");
		CategoryReq categoryReq = CategoryReq.testConstructor(categoryName, properties);
		String json = objectMapper.writeValueAsString(categoryReq);

		mockMvc.perform(post("/api/categories")
				.contentType(APPLICATION_JSON)
				.content(json)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value(categoryName))
			.andExpect(jsonPath("$.properties.키").value("벨류"))
			.andDo(print());

		assertThat(categoryRepository.findAll().size()).isEqualTo(1);

	}

	@Test
	@DisplayName("카테고리 조회")
	void test2() throws Exception {
		UserEntity user = getUser();
		for (int i = 0; i < 10; i++) {
			categoryRepository.save(Category.create(user, "카테고리" + i, Map.of("key1" + i, "value" + i, "key2", i)));
		}

		mockMvc.perform(get("/api/categories"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(10))
			.andExpect(jsonPath("$[0].name").value("카테고리0"))
			.andExpect(jsonPath("$[9].properties.key2").value(9))
			.andDo(print());
	}

	@Test
	@DisplayName("다른유저가 카테고리 조회하려하면 에러남")
	void test3() {
		//TODO
	}

	@Test
	@DisplayName("카테고리 수정")
	void test4() throws Exception {
		UserEntity testUser = getUser();
		String beforeCategoryName = "변경전카테고리";
		Map<String, Object> beforeProperties = Map.of("Key", "val");
		Category savedCategory = categoryRepository.save(
			Category.create(testUser, beforeCategoryName, beforeProperties));

		String afterCategoryName = "변경후카테고리";
		Map<String, Object> afterProperties = Map.of("updatedKey", "updatedVal");

		String json = objectMapper.writeValueAsString(CategoryReq.testConstructor(afterCategoryName, afterProperties));

		mockMvc.perform(patch("/api/categories/" + savedCategory.getId())
				.contentType(APPLICATION_JSON)
				.content(json)
			).andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value(afterCategoryName))
			.andDo(print());

		assertThat(categoryRepository.findAll().get(0).getName()).isEqualTo(afterCategoryName);

	}

}