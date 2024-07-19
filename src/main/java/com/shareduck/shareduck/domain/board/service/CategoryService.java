package com.shareduck.shareduck.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
public class CategoryService {

	private final CategoryRepository categoryRepository;

	private final UserRepository userRepository;

	/**
	 * 외부에서 사용할 메서드
	 *
	 * @param id
	 * @return Category
	 */
	public Category findById(Long id) {
		return categoryRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("해당 카테고리가 존재하지 않습니다")); //TODO
	}

	/**
	 * 외부에서 사용할 메서드
	 *
	 * @param category
	 * @param userId
	 * @return
	 */
	public void checkAccess(Category category, Long userId) {
		if (category.getUser().getId().equals(userId)) {
			return;
		}
		log.error("유저{}가 카테고리{}에 접근하려고 함", userId, category);
		throw new RuntimeException("접근권한이 없습니다");
	}

	/**
	 * 사용자의 새 카테고리 생성
	 *
	 * @param userId      유저아이디
	 * @param categoryReq {@link CategoryReq}
	 * @return CategoryRes    {@link CategoryRes}
	 */
	@Transactional(readOnly = false)
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

	@Transactional(readOnly = false)
	public CategoryRes updateCategory(Long userId, Long categoryId, CategoryReq categoryReq) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(RuntimeException::new);
		checkAccess(category, userId);

		category.updateName(categoryReq.getName());
		category.updateProperties(categoryReq.getProperties());
		return CategoryRes.from(category);
	}

}
