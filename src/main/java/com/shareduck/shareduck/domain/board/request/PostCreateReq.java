package com.shareduck.shareduck.domain.board.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PROTECTED;

@Schema(description = """
        사용자가 글쓰기를 클릭했을때 보내는 request로, CategoryId는(본인이 생성한) 반드시 포함되어야 한다.
        나머지 필드는 포함되지 않아도 상관없다.  서버에서는 PENDING(대기중) 상태로 DB에 저장된다.
        """)
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder(builderClassName = "TEST_BUILDER")
public class PostCreateReq {

    @NotNull(message = "카테고리아이디는 반드시 지정되어야 한다")
    private Long categoryId;

    @Schema(description = "타이틀")
    private String title;

    @Schema(description = "HTML 형식의 본문")
    private String content;

    @Schema(description = "해시태그 배열")
    private List<String> hashtags = new ArrayList<>();

    @Schema(description = "프로퍼티")
    private Map<String, Object> properties = new HashMap<>();

    @Schema(description = "썸네일 경로")
    private String thumbnailPath;

}
