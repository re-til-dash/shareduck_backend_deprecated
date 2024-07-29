package com.shareduck.shareduck.domain.upload.service;

import java.net.URI;

import com.shareduck.shareduck.domain.upload.request.UploadPostImageReq;

public interface UploadService {
	URI uploadPostImage(UploadPostImageReq uploadPostImageReq);
}
