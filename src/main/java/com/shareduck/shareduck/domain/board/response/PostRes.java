package com.shareduck.shareduck.domain.board.response;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.shareduck.shareduck.domain.board.entity.Hashtag;
import com.shareduck.shareduck.domain.board.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class PostRes {

	private Long id;

	private Long userId;

	private Long categoryId;

	private String title;

	private Map<String, Object> content;

	private List<String> hashtags;

	private Map<String, Object> properties;

	private String thumbnailPath;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	private boolean deleted;

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
		postRes.deleted = post.isDeleted();
		postRes.thumbnailPath = post.getThumbnailPath();
		return postRes;
	}

}
