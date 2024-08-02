package com.shareduck.shareduck.domain.board.request;

import static lombok.AccessLevel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@NotNull(message = "카테고리아이디는 반드시 지정되어야 한다")
	private Long categoryId;

	private String title;

	private Map<String, Object> content = new HashMap<>();

	private List<String> hashtags = new ArrayList<>();

	private Map<String, Object> properties = new HashMap<>();

	private String thumbnailPath;

}
