package com.shareduck.shareduck.domain.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * S3 설정
 * AWS 자격증명 방식 중 1개라도 만족하면 됨 => 난 aws cli로 할거임
 */
@Configuration
public class S3Config {

	@Bean
	public S3Client s3Client() {
		return S3Client.builder()
			.credentialsProvider(DefaultCredentialsProvider.create())
			.build();
	}
}
