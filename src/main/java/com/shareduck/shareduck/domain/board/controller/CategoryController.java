package com.shareduck.shareduck.domain.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.board.request.CategoryReq;
import com.shareduck.shareduck.domain.board.response.CategoryRes;
import com.shareduck.shareduck.domain.board.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("")
	public ResponseEntity<List<CategoryRes>> listCategories(CurrentUser currentUser) {
		List<CategoryRes> categories = categoryService.listUserCategories(currentUser.getUserId());

		return ResponseEntity.ok(categories); //TODO
	}

	@PostMapping("")
	public ResponseEntity<CategoryRes> createCategory(CurrentUser currentUser,
		@RequestBody @Valid CategoryReq categoryReq) {
		CategoryRes categoryRes = categoryService.createCategory(currentUser.getUserId(), categoryReq);
		return ResponseEntity.ok(categoryRes); //TODO
	}

	@PatchMapping("/{categoryId}")
	public ResponseEntity<CategoryRes> updateCategory(CurrentUser currentUser, @PathVariable long categoryId,
		@RequestBody CategoryReq categoryReq) {
		CategoryRes categoryRes = categoryService.updateCategory(currentUser.getUserId(), categoryId, categoryReq);
		return ResponseEntity.ok(categoryRes); //TODO
	}

}
