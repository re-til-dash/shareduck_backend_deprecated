package com.shareduck.shareduck.domain.board.request;

import static lombok.AccessLevel.*;

import java.util.Map;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class CategoryReq {

	@NotBlank
	@Length(min = 2, max = 50)
	private String name;

	private Map<String, Object> properties;

	public static CategoryReq testConstructor(String name, Map<String, Object> properties) {
		CategoryReq categoryReq = new CategoryReq();
		categoryReq.name = name;
		categoryReq.properties = properties;
		return categoryReq;
	}

}
