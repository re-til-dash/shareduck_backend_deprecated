package com.shareduck.shareduck.domain.board.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
class MemoControllerTest {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private MemoRepository memoRepository;
//
//	private final ObjectMapper objectMapper = new ObjectMapper();
//
//	@BeforeEach
//	public void init() {
//		memoRepository.deleteAll();
//		categoryRepository.deleteAll();
//	}
//
//	UserEntity getTestUser() {
//		return userRepository.findById(1L).get();
//	}
//
//	Category getTestCategory() {
//		return categoryRepository.findById(1L)
//			.orElse(categoryRepository.save(Category.create(getTestUser(), "테스트카테고리", null)));
//	}
//
//	@Test
//	@DisplayName("메모작성")
//	void test1() throws Exception {
//		UserEntity testUser = getTestUser();
//		Category testCategory = getTestCategory();
//		final String content = "메모컨텐츠";
//		MemoReq memoReq = MemoReq.testConstructor(testCategory.getId(), content);
//		String json = objectMapper.writeValueAsString(memoReq);
//
//		mockMvc.perform(post("/api/memos")
//				.contentType(APPLICATION_JSON)
//				.content(json)
//			).andExpect(status().is2xxSuccessful())
//			.andExpect(jsonPath("$.content").value(content))
//			.andDo(print());
//
//	}
//
//	@Test
//	@DisplayName("메모조회")
//	void test2() throws Exception {
//		UserEntity testUser = getTestUser();
//		Category testCategory = getTestCategory();
//		String content = "aaaa";
//		int iter_num = 111;
//		IntStream.range(0, 111).forEach(i -> {
//			memoRepository.save(Memo.create(testUser, testCategory, content + i));
//		});
//
//		mockMvc.perform(get("/api/memos")
//				.param("categoryId", String.valueOf(testCategory.getId()))
//			)
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.content.length()").value(100))
//			.andExpect(jsonPath("$.content[0].content").value(content + --iter_num))
//			.andDo(print());
//
//	}
//
//	@Test
//	@DisplayName("메모수정")
//	void test3() throws Exception {
//		UserEntity testUser = getTestUser();
//		Category testCategory = getTestCategory();
//		Memo savedMemo = memoRepository.save(Memo.create(testUser, testCategory, "AAAAA"));
//		final String updatedContent = "업데이트된컨텐츠";
//		String json = objectMapper.writeValueAsString(UpdateMemoReq.testConstructor(updatedContent));
//
//		mockMvc.perform(patch("/api/memos/" + savedMemo.getId())
//				.contentType(APPLICATION_JSON)
//				.content(json)
//			)
//			.andExpect(status().isOk())
//			.andDo(print());
//
//		assertThat(memoRepository.findAll().get(0).getContent()).isEqualTo(updatedContent);
//	}
//
//	@Test
//	@DisplayName("메모삭제")
//	void test4() throws Exception {
//		UserEntity testUser = getTestUser();
//		Category testCategory = getTestCategory();
//		Memo savedMemo = memoRepository.save(Memo.create(testUser, testCategory, "AAAAA"));
//		assertThat(memoRepository.findAll().size()).isEqualTo(1);
//
//		mockMvc.perform(delete("/api/memos/" + savedMemo.getId()))
//			.andExpect(status().isOk())
//			.andDo(print());
//
//		assertTrue(memoRepository.findAll().isEmpty());
//	}

}