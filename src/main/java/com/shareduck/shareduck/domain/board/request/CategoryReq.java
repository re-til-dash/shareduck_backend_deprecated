package com.shareduck.shareduck.domain.board.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Map;

import static lombok.AccessLevel.PROTECTED;

@Schema(description = "카테고리 생성시 또는 업데이트시 받는 request")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class CategoryReq {

    @Schema(description = "카테고리명 (중복가능)", example = "카테고리명1")
    @NotBlank
    @Length(min = 2, max = 50)
    private String name;

    @Schema(description = "카테고리 아이콘 경로")
    private String categoryIcon;

    @Schema(description = "일단 구현만 해둔상태, 실질적인 로직 ❌", example = "  {\"a\":1,\"b\":\"문자거나 숫자도 될 수 있음\"}     ")
    private Map<String, Object> properties;

    public static CategoryReq testConstructor(String name, Map<String, Object> properties) {
        CategoryReq categoryReq = new CategoryReq();
        categoryReq.name = name;
        categoryReq.properties = properties;
        return categoryReq;
    }

}
