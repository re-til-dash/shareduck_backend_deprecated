package com.shareduck.shareduck.domain.board.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Schema(description = """
        post를 업데이트할때 보내는 request로,
        """)
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class PostUpdateReq {

    @Schema(description = "제목")
    @NotBlank
    private String title;

    @Schema(description = "HTML 형식의 본문")
    @NotBlank
    private String content;

    @Schema(description = "해시태그 배열")
    @NotNull(message = "빈 객체라도 들어있어야 한다.")
    private List<String> hashTags;

    @Schema(description = "프로퍼티")
    @NotNull(message = "빈 객체라도 들어있어야 한다.")
    private Map<String, Object> properties;

    @Schema(description = "섬네일 경로")
    private String thumbnailPath;

}
