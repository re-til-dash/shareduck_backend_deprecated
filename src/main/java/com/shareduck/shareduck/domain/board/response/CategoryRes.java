package com.shareduck.shareduck.domain.board.response;

import com.shareduck.shareduck.domain.board.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Map;

@Schema(description = "카테고리 생성시 혹은 업데이트 후 클라이언트에게 반환되는 정보들")
@Getter
public class CategoryRes {

    @Schema(description = "카테고라이이디")
    private final Long id;

    @Schema(description = "카테고리 만든 유저(사용자) ID")
    private final Long userId;

    @Schema(description = "카테고리명")
    private final String name;

    @Schema(description = "카테고라 아이콘 경로")
    private final String categoryIcon;

    @Schema(description = "일단 구현만 해둔상태, 실질적인 로직 ❌", example = "  {\"a\":1,\"b\":\"문자거나 숫자도 될 수 있음\"}     ")
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
