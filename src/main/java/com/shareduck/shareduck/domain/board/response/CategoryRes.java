package com.shareduck.shareduck.domain.board.response;

import java.util.Map;

import com.shareduck.shareduck.domain.board.entity.Category;

import lombok.Getter;

@Getter
public class CategoryRes {

	private Long id;

	private String name;

	private Map<String, Object> properties;

	private CategoryRes(Long id, String name, Map<String, Object> properties) {
		this.id = id;
		this.name = name;
		this.properties = properties;
	}

	public static CategoryRes from(Category category) {
		return new CategoryRes(category.getId(), category.getName(), category.getProperties());
	}
}
