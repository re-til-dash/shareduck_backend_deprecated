package com.shareduck.shareduck.domain.board.response;

import com.shareduck.shareduck.domain.board.entity.Category;
import lombok.Getter;

import java.util.Map;

@Getter
public class CategoryRes {

    private final Long id;

    private final Long userId;

    private final String name;

    private final String categoryIcon;

    private final Map<String, Object> properties;

    private CategoryRes(Long id, Long userId, String name, String categoryIcon, Map<String, Object> properties) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.categoryIcon = categoryIcon;
        this.properties = properties;
    }

    public static CategoryRes from(Category category) {
        return new CategoryRes(category.getId(), category.getUser().getId(), category.getName(), category.getCategoryIcon(),
                category.getProperties());
    }
}
