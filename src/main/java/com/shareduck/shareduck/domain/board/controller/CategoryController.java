package com.shareduck.shareduck.domain.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.board.request.CategoryReq;
import com.shareduck.shareduck.domain.board.response.CategoryRes;
import com.shareduck.shareduck.domain.board.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping("")
	public ResponseEntity<?> listCategories(CurrentUser currentUser) {
		List<CategoryRes> categories = categoryService.listUserCategories(currentUser.getUserId());

		return ResponseEntity.ok(categories); //TODO
	}

	@PostMapping("")
	public ResponseEntity<?> createCategory(CurrentUser currentUser, @RequestBody CategoryReq categoryReq) {
		CategoryRes categoryRes = categoryService.createCategory(currentUser.getUserId(), categoryReq);
		return ResponseEntity.ok(categoryRes); //TODO
	}

	@PatchMapping("/{categoryId}")
	public ResponseEntity<?> updateCategory(CurrentUser currentUser, @PathVariable long categoryId,
		@RequestBody CategoryReq categoryReq) {
		CategoryRes categoryRes = categoryService.updateCategory(currentUser.getUserId(), categoryId, categoryReq);
		return ResponseEntity.ok(categoryRes); //TODO
	}

}
