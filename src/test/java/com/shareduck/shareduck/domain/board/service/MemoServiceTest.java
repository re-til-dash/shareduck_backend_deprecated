package com.shareduck.shareduck.domain.board.service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoServiceTest {
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
//	@Autowired
//	private MemoRepository memoRepository;
//	@Autowired
//	private MemoService memoService;
//
//	@AfterEach
//	void clean() {
//		memoRepository.deleteAll();
//	}
//
//	UserEntity getTestUser() {
//		return userRepository.findById(1L)
//			.get();
//	}
//
//	Category getTestCategory() {
//		return categoryRepository.findById(1L)
//			.orElse(categoryRepository.save(Category.create(getTestUser(), "테스트카테고리", null)));
//	}
//
//	@Test
//	@DisplayName("메모 생성")
//	void test1() {
//		UserEntity testUser = getTestUser();
//		Category category = getTestCategory();
//		String content = "content!!!!!!";
//		MemoReq memoReq = MemoReq.testConstructor(category.getId(), content);
//
//		MemoRes memoRes = memoService.createMemo(new CurrentUser(testUser.getId()), memoReq);
//
//		assertThat(memoRes.getCategoryId()).isEqualTo(category.getId());
//		assertThat(memoRes.getContent()).isEqualTo(content);
//		assertThat(memoRes.getUserId()).isEqualTo(testUser.getId());
//		System.out.println("memoRes = " + memoRes);
//	}
//
//	@Test
//	@DisplayName("메모 업데이트")
//	void test2() {
//		UserEntity testUser = getTestUser();
//		Category category = getTestCategory();
//		MemoReq memoReq = MemoReq.testConstructor(category.getId(), "업데이트전컨텐츠");
//		MemoRes memoRes = memoService.createMemo(new CurrentUser(testUser.getId()), memoReq);
//		Long memoId = memoRes.getId();
//		String updatedContent = "업데이트된컨텐츠임!!!!";
//		UpdateMemoReq updateMemoReq = UpdateMemoReq.testConstructor(updatedContent);
//
//		CurrentUser tmpCurrentUser = new CurrentUser(testUser.getId());
//
//		MemoRes updatedMemoRes = memoService.updateMemo(tmpCurrentUser, memoId, updateMemoReq);
//		assertThat(updatedMemoRes.getContent()).isEqualTo(updatedContent);
//
//		//실패케이스
//		assertThatThrownBy(() -> memoService.updateMemo(new CurrentUser(-1L), memoId, updateMemoReq))
//			.isInstanceOf(Exception.class);
//
//	}
//
//	@DisplayName("메모조회")
//	@Test
//	void test3() {
//		UserEntity testUser = getTestUser();
//		Category category = getTestCategory();
//		for (int i = 0; i < 30; i++) {
//			memoRepository.save(Memo.create(testUser, category, "컨텐츠" + i));
//		}
//
//		Page<MemoRes> memosByCategoryAndUser = memoService.getMemosBySearchConditions(
//			new CurrentUser(testUser.getId()),
//			new MemoSearchConditions(category.getId(), null),
//			PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"))
//		);
//
//		List<MemoRes> memos = memosByCategoryAndUser.getContent();
//		assertThat(memos.size()).isEqualTo(10);
//		assertThat(memos.get(0).getContent()).isEqualTo("컨텐츠" + 29);
//
//	}
//
//	@DisplayName("메모삭제테스트")
//	@Test
//	void test4() {
//		UserEntity testUser = getTestUser();
//		Category category = getTestCategory();
//		Memo savedMemo = memoRepository.save(Memo.create(testUser, category, "삭제될거임"));
//
//		assertThat(memoRepository.findAll().size()).isEqualTo(1);
//		assertThatThrownBy(() -> memoService.deleteMemo(new CurrentUser(-1L), savedMemo.getId()))
//			.isInstanceOf(Exception.class);
//		memoService.deleteMemo(new CurrentUser(testUser.getId()), savedMemo.getId());
//
//		assertThat(memoRepository.findAll().size()).isEqualTo(0);
//
//	}

}