package com.shareduck.shareduck.domain.upload.request;

import static lombok.AccessLevel.*;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class UploadPostImageReq {

	@NotNull(message = "모든 이미지에는 이미지가 속해있는 ID가 포함되어야 한다")
	private Long postId;

	@NotNull(message = "파일이 없으면 어쩌라는거임?")
	private MultipartFile file;
}
