package com.shareduck.shareduck.domain.board.response;

import com.shareduck.shareduck.domain.board.entity.Hashtag;
import com.shareduck.shareduck.domain.board.entity.Post;
import com.shareduck.shareduck.domain.board.enumerate.PostState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class PostRes {

    private Long id;

    private Long userId;

    private Long categoryId;

    private String title;

    private String content;

    private List<String> hashtags;

    private Map<String, Object> properties;

    private String thumbnailPath;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

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
