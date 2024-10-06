package com.shareduck.shareduck.domain.board.response;

import com.shareduck.shareduck.domain.board.entity.Memo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Schema(description = "메모 생성 후 클라이언트가 받는 결과값")
@Getter
public class MemoRes {

    @Schema(description = "메모ID")
    private Long id;

    @Schema(description = "사용자ID")
    private Long userId;

    @Schema(description = "메모가 생성된 카테고리의 ID")
    private Long categoryId;

    @Schema(description = "메모 본문", example = "메모 분문")
    private String content;

    @Schema(description = "메모 생성시간")
    private LocalDateTime createdAt;

    @Deprecated
    @Schema(description = "메모를 마지막 수정한 시간")
    private LocalDateTime lastModifiedAt;

    public static MemoRes from(@NonNull Memo memo) {
        MemoRes memoRes = new MemoRes();
        memoRes.id = memo.getId();
        memoRes.userId = memo.getUser().getId();
        memoRes.categoryId = memo.getCategory().getId();
        memoRes.content = memo.getContent();
        memoRes.createdAt = memo.getCreatedAt();
        memoRes.lastModifiedAt = memo.getModifiedAt();
        return memoRes;
    }

}
