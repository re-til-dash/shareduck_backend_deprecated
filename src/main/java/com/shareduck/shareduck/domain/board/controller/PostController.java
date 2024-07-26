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
import com.shareduck.shareduck.domain.board.request.PostCreateReq;
import com.shareduck.shareduck.domain.board.request.PostUpdateReq;
import com.shareduck.shareduck.domain.board.response.PostRes;
import com.shareduck.shareduck.domain.board.response.PostSimpleRes;
import com.shareduck.shareduck.domain.board.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class PostController {

	private final PostService postService;

	@PostMapping("/posts")
	public ResponseEntity<PostRes> createPost(CurrentUser currentUser,
		@RequestBody @Valid PostCreateReq postCreateReq) {
		PostRes postRes = postService.createPost(currentUser, postCreateReq);
		return ResponseEntity.ok(postRes);
	}

	@GetMapping("posts/{postId}")
	public ResponseEntity<PostRes> getSinglePost(CurrentUser currentUser, @PathVariable Long postId) {
		PostRes postRes = postService.getPostDetail(currentUser, postId);
		return ResponseEntity.ok(postRes);
	}

	@PatchMapping("posts/{postId}")
	public ResponseEntity<PostRes> updatePost(CurrentUser currentUser, @PathVariable Long postId,
		@RequestBody @Valid PostUpdateReq postUpdateReq) {
		PostRes postRes = postService.updatePost(currentUser, postId, postUpdateReq);
		return ResponseEntity.ok(postRes);
	}

	@DeleteMapping("posts/{postId}")
	public ResponseEntity<PostRes> deletePost(CurrentUser currentUser, @PathVariable Long postId) {
		postService.deletePost(currentUser, postId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("posts-list")
	public ResponseEntity<Page<PostSimpleRes>> getSimplePostByUserAndCategory(CurrentUser currentUser,
		@RequestParam Long categoryId,
		@PageableDefault(size = 20) Pageable pageable
	) {
		Page<PostSimpleRes> postSimpleResponsePage = postService.getSimplepostPage(currentUser,
			categoryId, pageable);
		return ResponseEntity.ok(postSimpleResponsePage);
	}

}
