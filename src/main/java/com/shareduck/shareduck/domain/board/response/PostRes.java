package com.shareduck.shareduck.domain.board.response;

import com.shareduck.shareduck.domain.board.entity.Hashtag;
import com.shareduck.shareduck.domain.board.entity.Post;
import com.shareduck.shareduck.domain.board.enumerate.PostState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Schema(description = """
        Post에 대한 전체 정보를 담고 있는 객체
        """)
@Getter
@NoArgsConstructor(access = PRIVATE)
public class PostRes {

    @Schema(description = "post ID")
    private Long id;

    @Schema(description = "사용자 ID")
    private Long userId;

    @Schema(description = "카테고리 ID")
    private Long categoryId;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "HTML 형식의 본문")
    private String content;

    @Schema(description = "해시태그 배열")
    private List<String> hashtags;

    @Schema(description = "프로퍼티")
    private Map<String, Object> properties;

    @Schema(description = "썸네일 경로")
    private String thumbnailPath;

    @Schema(description = "생성시간(timestamp)")
    private LocalDateTime createdAt;

    @Schema(description = "수정시간(timestamp)")
    private LocalDateTime modifiedAt;

    @Schema(description = "상태")
    private PostState state;

    public static PostRes from(Post post) {
        PostRes postRes = new PostRes();
        postRes.id = post.getId();
        postRes.userId = post.getUser().getId();
        postRes.categoryId = post.getCategory().getId();
        postRes.title = post.getTitle();
        postRes.content = post.getContent();
        postRes.hashtags = post.getHashtags()
                .stream().map(Hashtag::getTag).toList();
        postRes.properties = post.getProperties();
        postRes.createdAt = post.getCreatedAt();
        postRes.modifiedAt = post.getModifiedAt();
        postRes.state = post.getState();
        postRes.thumbnailPath = post.getThumbnailPath();
        return postRes;
    }

}
