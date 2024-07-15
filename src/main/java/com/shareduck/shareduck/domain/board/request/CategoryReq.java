package com.shareduck.shareduck.domain.board.request;

import java.util.Map;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "TestBuilder")
public class CategoryReq {

	@NotBlank
	@Length(min = 2, max = 50)
	private String name;

	private Map<String, Object> properties;

}
