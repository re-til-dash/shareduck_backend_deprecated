package com.shareduck.shareduck.domain.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.board.request.MemoReq;
import com.shareduck.shareduck.domain.board.request.UpdateMemoReq;
import com.shareduck.shareduck.domain.board.response.MemoRes;
import com.shareduck.shareduck.domain.board.service.MemoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class MemoController {

	private final MemoService memoService;

	@PostMapping("")
	public ResponseEntity<MemoRes> createMemo(CurrentUser currentUser, @RequestBody @Valid MemoReq memoReq) {
		MemoRes memoRes = memoService.createMemo(currentUser, memoReq);
		return ResponseEntity.status(201).body(memoRes);
	}

	@Deprecated
	@PatchMapping("/{memoId}")
	public ResponseEntity<MemoRes> updateMemo(CurrentUser currentUser, @PathVariable long memoId,
		@RequestBody @Valid UpdateMemoReq updateMemoReq) {
		MemoRes memoRes = memoService.updateMemo(currentUser, memoId, updateMemoReq);
		return ResponseEntity.ok(memoRes);
	}

	@DeleteMapping("/{memoId}")
	public ResponseEntity<Void> deleteMemo(CurrentUser currentUser, @PathVariable long memoId) {
		memoService.deleteMemo(currentUser, memoId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("")
	public ResponseEntity<Page<MemoRes>> listMemosWithCategory(CurrentUser currentUser,
		@RequestParam Long categoryId,
		@PageableDefault(size = 100) Pageable pageable) {
		Page<MemoRes> memos = memoService.getMemoByUserIdAndCategoryId(currentUser,
			categoryId, pageable);
		return ResponseEntity.ok(memos);

	}

}