package com.shareduck.shareduck.domain.board.request;

import static lombok.AccessLevel.*;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder(builderClassName = "TEST_BUILDER")
public class PostCreateReq {

	@NotNull
	private Long categoryId;

	@NotBlank
	private String title;

	@NotNull
	private Map<String, Object> content;

	@NotNull
	private List<String> hashtags;

	@NotNull
	private Map<String, Object> properties;

	private String thumbnailPath;

}
