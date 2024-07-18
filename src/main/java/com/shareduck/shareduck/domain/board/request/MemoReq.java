package com.shareduck.shareduck.domain.board.request;

import static lombok.AccessLevel.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class MemoReq {

	@NotNull
	private Long categoryId;

	@NotBlank
	private String content;

	public static MemoReq testConstructor(Long categoryId, String content) {
		return new MemoReq(categoryId, content);
	}

}
