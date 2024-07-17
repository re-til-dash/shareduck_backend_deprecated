package com.shareduck.shareduck.domain.board.response;

import java.util.Map;

import com.shareduck.shareduck.domain.board.entity.Category;

import lombok.Getter;

@Getter
public class CategoryRes {

	private final Long id;

	private final Long userId;

	private final String name;

	private final Map<String, Object> properties;

	private CategoryRes(Long id, Long userId, String name, Map<String, Object> properties) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.properties = properties;
	}

	public static CategoryRes from(Category category) {
		return new CategoryRes(category.getId(), category.getUser().getId(), category.getName(),
			category.getProperties());
	}
}
