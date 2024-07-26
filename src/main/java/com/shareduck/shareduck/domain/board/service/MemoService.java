package com.shareduck.shareduck.domain.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shareduck.shareduck.common.exception.BusinessLogicException;
import com.shareduck.shareduck.common.request.dto.CurrentUser;
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
@Transactional
public class MemoService {

	private final MemoRepository memoRepository;

	private final UserRepository userRepository;

	private final CategoryService categoryService;

	/**
	 * 새로운 메모 생성
	 *
	 * @param currentUser currentUser
	 * @param memoReq     {@link MemoReq}
	 * @return MemoRes {@link MemoRes}
	 */
	public MemoRes createMemo(CurrentUser currentUser, MemoReq memoReq) {
		UserEntity user = userRepository.findById(currentUser.getUserId())
			.orElseThrow(() -> new BusinessLogicException(AuthExceptionCode.USER_NOT_FOUND));

		Category category = categoryService.findCategoryByIdAndCheckAccess(memoReq.getCategoryId(), currentUser);

		Memo newMemo = Memo.create(user, category, memoReq.getContent());
		memoRepository.save(newMemo);
		return MemoRes.from(newMemo);
	}

	/**
	 * 메모 삭제
	 *
	 * @param currentUser 현재유저
	 * @param memoId      메모ID
	 */
	public void deleteMemo(CurrentUser currentUser, long memoId) {
		Memo memo = memoRepository.findById(memoId)
			.orElseThrow(RuntimeException::new);
		if (!canAccess(currentUser.getUserId(), memo)) {
			throw new BusinessLogicException(AuthExceptionCode.UNAUTHENTICATED);
		}
		memoRepository.delete(memo);
	}

	/**
	 * 메모 변경
	 *
	 * @param currentUser   currentUser
	 * @param memoId        메모Id
	 * @param updateMemoReq {@link UpdateMemoReq}
	 */
	public MemoRes updateMemo(CurrentUser currentUser, Long memoId, UpdateMemoReq updateMemoReq) {
		Memo memo = memoRepository.findById(memoId)
			.orElseThrow(RuntimeException::new);
		if (!canAccess(currentUser.getUserId(), memo)) {
			throw new BusinessLogicException(AuthExceptionCode.UNAUTHENTICATED);
		}
		memo.changeContent(updateMemoReq.getContent());
		return MemoRes.from(memo);
	}

	/**
	 * 접근가능한지 확인
	 *
	 * @param userId 유저아이디
	 * @param memo   메모
	 * @return 접근가능여부
	 */
	private boolean canAccess(Long userId, Memo memo) {
		return userId.equals(memo.getUser().getId());
	}

	/**
	 * 특정 사용자의 특정 카테고리의 메모 최신순으로 가져오기
	 *
	 * @param currentUser 현재유저
	 * @param categoryId  카테고리아이디
	 * @param pageable    페이징객체
	 * @return Memores 페이징객체
	 */
	@Transactional(readOnly = true)
	public Page<MemoRes> getMemoByUserIdAndCategoryId(CurrentUser currentUser, long categoryId, Pageable pageable) {
		categoryService.findCategoryByIdAndCheckAccess(categoryId, currentUser);
		Page<Memo> Memos = memoRepository.findByCategoryIdOrderByIdDesc(categoryId, pageable);
		return Memos.map(MemoRes::from);
	}
}
