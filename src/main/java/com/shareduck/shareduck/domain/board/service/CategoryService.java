package com.shareduck.shareduck.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shareduck.shareduck.domain.board.entity.Category;
import com.shareduck.shareduck.domain.board.repository.CategoryRepository;
import com.shareduck.shareduck.domain.board.request.CategoryReq;
import com.shareduck.shareduck.domain.board.response.CategoryRes;
import com.shareduck.shareduck.domain.user.entity.UserEntity;
import com.shareduck.shareduck.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	private final UserRepository userRepository;

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
		Category newCategory = Category.create(userEntity, categoryReq.getName(), categoryReq.getProperties());
		categoryRepository.save(newCategory);
		return CategoryRes.from(newCategory);
	}

	/**
	 * 사용자가 가지고 있는 카테고리 전부 반환
	 *
	 * @param userId 유저아이디
	 * @return 사용자가 가진 모든 카테고리를 담은 {@link CategoryRes} 객체의 리스트
	 */
	public List<CategoryRes> listUserCategories(Long userId) {
		return categoryRepository.findCategoriesByUserId(userId)
			.stream()
			.map(CategoryRes::from)
			.toList();
	}

	public CategoryRes updateCategory(Long userId, Long categoryId, CategoryReq categoryReq) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(RuntimeException::new);
		if (!canAccess(category, userId)) {
			throw new RuntimeException();//TODO
		}
		category.changeName(categoryReq.getName());
		category.changeProperties(categoryReq.getProperties());
		return CategoryRes.from(category);
	}

	private boolean canAccess(Category category, Long userId) {
		return category.getUser().getId().equals(userId);
	}
}
