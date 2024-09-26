package com.shareduck.shareduck.domain.board.service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceTest {
//
//	@Autowired
//	private CategoryService categoryService;
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@AfterEach
//	void clean() {
//		categoryRepository.deleteAll();
//	}
//
//	UserEntity getTestUser() {
//		return userRepository.findById(1L)
//			.get();
//	}
//
//	@Test
//	@DisplayName("카테고리 생성")
//	void test1() {
//		List<Category> all = categoryRepository.findAll();
//		System.out.println(all);
//		UserEntity testUser = getTestUser();
//		String categoryName = "새로운캬테고리";
//		Map<String, Object> properties = Map.of("key", "value", "key2", "value2");
//		CategoryReq categoryReq = CategoryReq.testConstructor(categoryName, properties);
//
//		CategoryRes categoryRes = categoryService.createCategory(testUser.getId(), categoryReq);
//		assertThat(categoryRes.getName()).isEqualTo(categoryName);
//		assertThat(categoryRes.getProperties())
//			.containsEntry("key", "value")
//			.containsEntry("key2", "value2");
//	}
//
//	@Test
//	@DisplayName("카테고리 업데이트")
//	void test2() {
//		UserEntity testUser = getTestUser();
//		String categoryName = "새로운캬테고리";
//		Map<String, Object> properties = Map.of("key", "value", "key2", "value2");
//		CategoryReq categoryReq = CategoryReq.testConstructor(categoryName, properties);
//		CategoryRes categoryRes = categoryService.createCategory(testUser.getId(), categoryReq);
//
//		String updatedCategoryName = "업데이트된카테고리";
//		Map<String, Object> updatedProperties = Map.of("updateKey", 1, "updatedKey2", "updatedValue2");
//		CategoryReq updatedCategoryReq = CategoryReq.testConstructor(updatedCategoryName, updatedProperties);
//
//		CategoryRes updatedCategoryRes = categoryService.updateCategory(new CurrentUser(testUser.getId()),
//			categoryRes.getId(),
//			updatedCategoryReq);
//
//		assertThat(updatedCategoryRes.getName()).isEqualTo(updatedCategoryName);
//		assertThat(updatedCategoryRes.getProperties())
//			.containsEntry("updateKey", 1)
//			.containsEntry("updatedKey2", "updatedValue2");
//	}
//
//	@Test
//	@DisplayName("다른 유저의 카테고리리를 업데이트 하려는 경우 업데이트 실패")
//	void test3() {
//		UserEntity testUser = getTestUser();
//		String categoryName = "새로운캬테고리";
//		Map<String, Object> properties = Map.of("key", "value", "key2", "value2");
//		CategoryReq categoryReq = CategoryReq.testConstructor(categoryName, properties);
//		CategoryRes categoryRes = categoryService.createCategory(testUser.getId(), categoryReq);
//
//		String updatedCategoryName = "업데이트된카테고리";
//		Map<String, Object> updatedProperties = Map.of("updateKey", 1, "updatedKey2", "updatedValue2");
//		CategoryReq updatedCategoryReq = CategoryReq.testConstructor(categoryName, properties);
//
//		assertThatThrownBy(
//			() -> categoryService.updateCategory(new CurrentUser(-1L), categoryRes.getId(), updatedCategoryReq))
//			.isInstanceOf(RuntimeException.class);
//	}
//
//	@Test
//	@DisplayName("유저의 카테고리 조회")
//	void test4() {
//		UserEntity testUser = getTestUser();
//		for (int i = 0; i < 5; i++) {
//			CategoryReq categoryReq = CategoryReq.testConstructor(
//				"카테고리" + i,
//				Map.of("key", "value", "key2", "value2")
//			);
//			categoryService.createCategory(testUser.getId(), categoryReq);
//		}
//		List<CategoryRes> categoryRes = categoryService.listUserCategories(new CurrentUser(testUser.getId()));
//		assertThat(categoryRes.size()).isEqualTo(5);
//		for (int i = 0; i < 5; i++) {
//			assertThat(categoryRes.get(i).getName()).isEqualTo("카테고리" + i);
//		}
//
//	}

}