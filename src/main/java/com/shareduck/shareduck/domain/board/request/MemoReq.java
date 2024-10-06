package com.shareduck.shareduck.domain.board.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Schema(description = "메모 생성시 받는 정보들")
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class MemoReq {

    @Schema(description = "메모를 생성할 카테고리의 ID")
    @NotNull
    private Long categoryId;

    @Schema(description = "메모 본문", example = "메모 본문입니다")
    @NotBlank
    private String content;

    public static MemoReq testConstructor(Long categoryId, String content) {
        return new MemoReq(categoryId, content);
    }

}
