package com.shareduck.shareduck.domain.upload.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shareduck.shareduck.domain.upload.request.UploadPostImageReq;
import com.shareduck.shareduck.domain.upload.service.UploadService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class UploadController {

	private final UploadService uploadService;

	@PostMapping("/post-image")
	public ResponseEntity<?> uploadImage(@ModelAttribute @Valid UploadPostImageReq uploadPostImageReq) {
		URI createdFileURI = uploadService.uploadPostImage(uploadPostImageReq);
		return ResponseEntity.created(createdFileURI).build();
	}

}
