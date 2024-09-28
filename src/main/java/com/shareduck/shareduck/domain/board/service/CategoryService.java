package com.shareduck.shareduck.domain.board.service;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.board.entity.Category;
import com.shareduck.shareduck.domain.board.repository.CategoryRepository;
import com.shareduck.shareduck.domain.board.request.CategoryReq;
import com.shareduck.shareduck.domain.board.response.CategoryRes;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    /**
     * 외부에서 사용할 메서드
     *
     * @param categoryId 카테고리아이디
     * @return Category
     */
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("해당 카테고리가 존재하지 않습니다")); //TODO
    }

    public Category findCategoryByIdAndCheckAccess(Long categoryId, CurrentUser currentUser) {
        Category category = findCategoryById(categoryId);
        if (!category.getUser().getId().equals(currentUser.getUserId())) {
            log.error("유저{}가 카테고리{}에 접근하려고 함", currentUser.getUserId(), category);
            throw new RuntimeException("접근권한이 없습니다");
        }
        return category;
    }

    /**
     * 사용자의 새 카테고리 생성
     *
     * @param userId      유저아이디
     * @param categoryReq {@link CategoryReq}
     * @return CategoryRes    {@link CategoryRes}
     */
    public CategoryRes createCategory(Long userId, CategoryReq categoryReq) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);
        Category newCategory = Category.create(userEntity, categoryReq.getName(), categoryReq.getCategoryIcon(), categoryReq.getProperties());
        categoryRepository.save(newCategory);
        return CategoryRes.from(newCategory);
    }

    /**
     * 사용자가 가지고 있는 카테고리 전부 반환
     *
     * @param currentUser 현재유저
     * @return 사용자가 가진 모든 카테고리를 담은 {@link CategoryRes} 객체의 리스트
     */
    @Transactional(readOnly = true)
    public List<CategoryRes> listUserCategories(CurrentUser currentUser) {
        return categoryRepository.findCategoriesByUserId(currentUser.getUserId())
                .stream()
                .map(CategoryRes::from)
                .toList();
    }

    public CategoryRes updateCategory(CurrentUser currentUser, Long categoryId, CategoryReq categoryReq) {
        Category category = findCategoryByIdAndCheckAccess(categoryId, currentUser);
        category.updateName(categoryReq.getName());
        category.updateProperties(categoryReq.getProperties());
        return CategoryRes.from(category);
    }

    public CategoryRes getSingleCategory(CurrentUser currentUser, long categoryId) {
        Category category = findCategoryByIdAndCheckAccess(categoryId, currentUser);
        return CategoryRes.from(category);
    }
}
