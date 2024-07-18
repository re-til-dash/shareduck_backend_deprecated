package com.shareduck.shareduck.domain.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shareduck.shareduck.common.exception.BusinessLogicException;
import com.shareduck.shareduck.common.security.exception.AuthExceptionCode;
import com.shareduck.shareduck.domain.board.entity.Category;
import com.shareduck.shareduck.domain.board.entity.Memo;
import com.shareduck.shareduck.domain.board.repository.MemoRepository;
import com.shareduck.shareduck.domain.board.request.MemoReq;
import com.shareduck.shareduck.domain.board.request.UpdateMemoReq;
import com.shareduck.shareduck.domain.board.response.MemoRes;
import com.shareduck.shareduck.domain.user.entity.UserEntity;
import com.shareduck.shareduck.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemoService {

	private final MemoRepository memoRepository;

	private final UserRepository userRepository;

	private final CategoryService categoryService;

	/**
	 * 새로운 메모 생성
	 *
	 * @param userId
	 * @param memoReq {@link MemoReq}
	 * @return MemoRes {@link MemoRes}
	 */
	@Transactional(readOnly = false)
	public MemoRes createMemo(Long userId, MemoReq memoReq) {
		UserEntity user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessLogicException(AuthExceptionCode.USER_NOT_FOUND));

		Category category = categoryService.findById(memoReq.getCategoryId());
		categoryService.checkAccess(category, userId);

		Memo newMemo = Memo.from(user, category, memoReq.getContent());
		memoRepository.save(newMemo);
		return MemoRes.from(newMemo);
	}

	/**
	 * 메모 삭제
	 *
	 * @param userId
	 * @param memoId
	 */
	@Transactional(readOnly = false)
	public void deleteMemo(long userId, long memoId) {
		Memo memo = memoRepository.findById(memoId)
			.orElseThrow(RuntimeException::new);
		if (!canAccess(userId, memo)) {
			throw new BusinessLogicException(AuthExceptionCode.UNAUTHENTICATED);
		}
		memoRepository.delete(memo);
	}

	/**
	 * 메모 변경
	 *
	 * @param userId
	 * @param memoId
	 * @param updateMemoReq {@link UpdateMemoReq}
	 */
	@Transactional(readOnly = false)
	public MemoRes updateMemo(long userId, Long memoId, UpdateMemoReq updateMemoReq) {
		Memo memo = memoRepository.findById(memoId)
			.orElseThrow(RuntimeException::new);
		if (!canAccess(userId, memo)) {
			throw new BusinessLogicException(AuthExceptionCode.UNAUTHENTICATED);
		}
		memo.changeContent(updateMemoReq.getContent());
		return MemoRes.from(memo);
	}

	/**
	 * 접근가능한지 확인
	 *
	 * @param userId
	 * @param memo
	 * @return 접근가능여부
	 */
	private boolean canAccess(Long userId, Memo memo) {
		return userId.equals(memo.getUser().getId());
	}

	/**
	 * 특정 사용자의 특정 카테고리의 메모 최신순으로 가져오기
	 *
	 * @param userId
	 * @param categoryId
	 * @param pageable
	 * @return
	 */
	public Page<MemoRes> getMemosByCategoryAndUser(Long userId, long categoryId, Pageable pageable) {
		Page<Memo> Memos = memoRepository.findByUserIdAndCategoryIdOrderByIdDesc(userId,
			categoryId, pageable);
		return Memos.map(MemoRes::from);
	}
}
