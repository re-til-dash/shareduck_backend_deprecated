package com.shareduck.shareduck.domain.board.controller;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.board.request.CategoryReq;
import com.shareduck.shareduck.domain.board.response.CategoryRes;
import com.shareduck.shareduck.domain.board.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryRes>> listCategories(CurrentUser currentUser) {
        List<CategoryRes> categories = categoryService.listUserCategories(currentUser);

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
        CategoryRes categoryRes = categoryService.updateCategory(currentUser, categoryId, categoryReq);
        return ResponseEntity.ok(categoryRes); //TODO
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryRes> getSingleCategory(CurrentUser currentUser, @PathVariable long categoryId) {
        CategoryRes categoryRes = categoryService.getSingleCategory(currentUser, categoryId);
        return ResponseEntity.ok(categoryRes);
    }

}
