package com.shareduck.shareduck.domain.upload.service;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.shareduck.shareduck.domain.upload.request.UploadPostImageReq;

@Service
@Profile("test")
public class UploadServiceMock implements UploadService {
	@Override
	public URI uploadPostImage(UploadPostImageReq uploadPostImageReq) {
		return null;
	}
}
