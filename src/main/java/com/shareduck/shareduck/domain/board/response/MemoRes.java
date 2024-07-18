package com.shareduck.shareduck.domain.board.response;

import java.time.LocalDateTime;

import com.shareduck.shareduck.domain.board.entity.Memo;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class MemoRes {

	private Long id;

	private Long userId;

	private Long categoryId;

	private String content;

	private LocalDateTime createdAt;

	private LocalDateTime lastModifiedAt;

	public static MemoRes from(@NonNull Memo memo) {
		MemoRes memoRes = new MemoRes();
		memoRes.id = memo.getId();
		memoRes.userId = memo.getUser().getId();
		memoRes.categoryId = memo.getCategory().getId();
		memoRes.content = memo.getContent();
		memoRes.createdAt = memo.getCreatedAt();
		memoRes.lastModifiedAt = memo.getModifiedAt();
		return memoRes;
	}

}
