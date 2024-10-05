package com.shareduck.shareduck.domain.board.response;

import com.shareduck.shareduck.domain.board.entity.Hashtag;
import com.shareduck.shareduck.domain.board.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Schema(description = "post list조회시 쓰는 객체")
@Getter
@AllArgsConstructor
public class PostSimpleRes {

    @Schema(description = "post ID")
    private Long id;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "HTML 형식의 본문")
    private String thumbnailPath;

    private Map<String, Object> properties;

    @Schema(description = "생성시간(timestamp)")
    private LocalDateTime createdAt;

    @Schema(description = "수정시간(timestamp)")
    private LocalDateTime modifiedAt;

    @Schema(description = "해시태그 배열")
    private List<String> hashtags;

    public static PostSimpleRes from(Post post) {
        return new PostSimpleRes(
                post.getId(),
                post.getTitle(),
                post.getThumbnailPath(),
                post.getProperties(),
                post.getCreatedAt(),
                post.getModifiedAt(),
                post.getHashtags()
                        .stream().map(Hashtag::getTag).toList()
        );
    }

}
