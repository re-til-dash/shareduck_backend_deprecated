package com.shareduck.shareduck.domain.board.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.shareduck.shareduck.domain.board.entity.Category;
import com.shareduck.shareduck.domain.board.entity.Memo;
import com.shareduck.shareduck.domain.board.repository.CategoryRepository;
import com.shareduck.shareduck.domain.board.repository.MemoRepository;
import com.shareduck.shareduck.domain.board.request.MemoReq;
import com.shareduck.shareduck.domain.board.request.UpdateMemoReq;
import com.shareduck.shareduck.domain.board.response.MemoRes;
import com.shareduck.shareduck.domain.user.entity.UserEntity;
import com.shareduck.shareduck.domain.user.repository.UserRepository;

@SpringBootTest
class MemoServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MemoRepository memoRepository;
	@Autowired
	private MemoService memoService;

	@AfterEach
	void clean() {
		memoRepository.deleteAll();
	}

	UserEntity getTestUser() {
		return userRepository.findById(1L)
			.get();
	}

	@Test
	@DisplayName("메모 생성")
	void test1() {
		UserEntity testUser = getTestUser();
		Category category = categoryRepository.findById(1L).get();
		String content = "content!!!!!!";
		MemoReq memoReq = MemoReq.testConstructor(category.getId(), content);

		MemoRes memoRes = memoService.createMemo(testUser.getId(), memoReq);

		assertThat(memoRes.getCategoryId()).isEqualTo(category.getId());
		assertThat(memoRes.getContent()).isEqualTo(content);
		assertThat(memoRes.getUserId()).isEqualTo(testUser.getId());
		System.out.println("memoRes = " + memoRes);
	}

	@Test
	@DisplayName("메모 업데이트")
	void test2() {
		UserEntity testUser = getTestUser();
		Category category = categoryRepository.findById(1L).get();
		MemoReq memoReq = MemoReq.testConstructor(category.getId(), "업데이트전컨텐츠");
		MemoRes memoRes = memoService.createMemo(testUser.getId(), memoReq);
		Long memoId = memoRes.getId();
		String updatedContent = "업데이트된컨텐츠임!!!!";
		UpdateMemoReq updateMemoReq = UpdateMemoReq.testConstructor(updatedContent);

		MemoRes updatedMemoRes = memoService.updateMemo(testUser.getId(), memoId, updateMemoReq);
		assertThat(updatedMemoRes.getContent()).isEqualTo(updatedContent);

		//실패케이스
		assertThatThrownBy(() -> memoService.updateMemo(-1L, memoId, updateMemoReq))
			.isInstanceOf(Exception.class);

	}

	@DisplayName("메모조회")
	@Test
	void test3() {
		UserEntity testUser = getTestUser();
		Category category = categoryRepository.findById(1L).get();
		for (int i = 0; i < 30; i++) {
			memoRepository.save(Memo.from(testUser, category, "컨텐츠" + i));
		}

		Page<MemoRes> memosByCategoryAndUser = memoService.getMemosByCategoryAndUser(
			testUser.getId(),
			category.getId(),
			PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"))
		);

		List<MemoRes> memos = memosByCategoryAndUser.getContent();
		assertThat(memos.size()).isEqualTo(10);
		assertThat(memos.get(0).getContent()).isEqualTo("컨텐츠" + 29);

	}

	@DisplayName("메모삭제테스트")
	@Test
	void test4() {
		UserEntity testUser = getTestUser();
		Category category = categoryRepository.findById(1L).get();
		Memo savedMemo = memoRepository.save(Memo.from(testUser, category, "삭제될거임"));

		assertThat(memoRepository.findAll().size()).isEqualTo(1);
		assertThatThrownBy(() -> memoService.deleteMemo(-1L, savedMemo.getId()))
			.isInstanceOf(Exception.class);
		memoService.deleteMemo(1L, savedMemo.getId());

		assertThat(memoRepository.findAll().size()).isEqualTo(0);

	}

}