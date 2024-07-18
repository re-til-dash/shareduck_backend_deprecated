package com.shareduck.shareduck.domain.board.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemoReq {

	@NotNull
	private Long categoryId;

	@NotBlank
	private String content;

}
