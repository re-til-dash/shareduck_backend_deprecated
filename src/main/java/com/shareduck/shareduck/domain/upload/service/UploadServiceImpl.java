package com.shareduck.shareduck.domain.upload.service;

import static java.util.Objects.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.shareduck.shareduck.domain.upload.repository.ImageRepository;
import com.shareduck.shareduck.domain.upload.request.UploadPostImageReq;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@RequiredArgsConstructor
@Service
@Profile("!test")
public class UploadServiceImpl implements UploadService {

	@Value("${aws.s3.bucket.name}")
	private String BUCKET_NAME;

	@Value("${aws.cloudfront.host}")
	private String CLOUDFRONT_HOST;

	@Value("${aws.s3.postImage.root}")
	private String POST_IMAGE_PATH;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

	private final S3Client s3;

	private final ImageRepository imageRepository;

	/**
	 * S3에 파일 업로드하는 메서드
	 *
	 * @param file               MultiparFile 객체
	 * @param key                s3 경로
	 * @param contentType        파일컨텐츠타입
	 * @param contentDisposition contentDisposition 헤더
	 * @param metadata           메타데이타
	 */
	private void uploadFileToS3(@NonNull MultipartFile file, @NonNull String key, String contentType,
		@Nullable String contentDisposition, @Nullable Map<String, String> metadata) {
		try {
			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(BUCKET_NAME)
				.key(key)
				.contentType(contentType)
				.contentDisposition(contentDisposition)
				.metadata(metadata)
				.build();

			s3.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
			log.info("S3버킷의  {}경로에 파일을 올림!!!", key);
		} catch (Exception e) {
			log.error("error uploading file: {}", e.getMessage());
			throw new RuntimeException("파일업로드 실패");
		}
	}

	private String generatePostImageKey(@NonNull Long postId, @NotNull String ext) {
		String timestamp = LocalDateTime.now().format(FORMATTER);
		return String.format("%s/%s/%s%s", POST_IMAGE_PATH, postId, timestamp, ext);
	}

	public URI uploadPostImage(UploadPostImageReq uploadPostImageReq) {
		MultipartFile file = uploadPostImageReq.getFile();
		String ext = getExt(requireNonNull(file.getOriginalFilename()));
		String key = generatePostImageKey(uploadPostImageReq.getPostId(), ext);

		uploadFileToS3(file, key, file.getContentType(), null, null);

		return UriComponentsBuilder
			.newInstance()
			.scheme("https")
			.host(CLOUDFRONT_HOST)
			.pathSegment(key)
			.build()
			.toUri();
	}

	private static String getExt(String fileName) { //TODO null일 경우? 생각하기
		return fileName.substring(fileName.lastIndexOf("."));
	}

}
