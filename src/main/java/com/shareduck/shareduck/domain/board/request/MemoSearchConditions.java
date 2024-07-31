package com.shareduck.shareduck.domain.board.request;

import jakarta.validation.constraints.NotNull;

public record MemoSearchConditions
	(
		@NotNull(message = "카테고라아이디는 필수임!!")
		Long categoryId,
		String keyword
	) {

}
