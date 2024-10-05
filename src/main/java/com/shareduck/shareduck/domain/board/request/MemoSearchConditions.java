package com.shareduck.shareduck.domain.board.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "메모 검색 조건")
public record MemoSearchConditions
        (
                @NotNull(message = "카테고라아이디는 필수임!!")
                @Schema(description = "카테고리의 ID", example = "123")
                Long categoryId,

                @Schema(description = "검색할 키워드", example = "메모 제목")
                String keyword
        ) {

}
