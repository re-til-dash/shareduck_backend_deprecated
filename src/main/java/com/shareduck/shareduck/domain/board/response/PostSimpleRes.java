package com.shareduck.shareduck.domain.board.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.shareduck.shareduck.domain.board.entity.Hashtag;
import com.shareduck.shareduck.domain.board.entity.Post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "post list조회할때 쓸건데 지금은 일단  보류할거임")
@Getter
@AllArgsConstructor
public class PostSimpleRes {

	private Long id;

	private String title;

	private String thumbnailPath;

	private Map<String, Object> properties;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

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
