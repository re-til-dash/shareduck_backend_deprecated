package com.shareduck.shareduck.domain.board.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateMemoReq {

	@NotBlank
	private String content;
}
