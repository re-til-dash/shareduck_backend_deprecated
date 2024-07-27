package com.shareduck.shareduck.domain.upload.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
/**
 * TODO 협의후에 생각
 */
public class ImageRepository {

	private final JdbcTemplate jdbcTemplate;

	private final String IMAGE_TABLE_NAME = "IMAGE_LOG";

	@PostConstruct
	public void initImageTable() {
		try {

			String q = """
				CREATE TABLE IF NOT EXISTS %s 
				(id BIGINT AUTO_INCREMENT PRIMARY KEY, 
				POST_ID BIGINT NOT NULL, 
				IMAGE_KEY VARCHAR(200) NOT NULL)
				""".formatted(IMAGE_TABLE_NAME);

			jdbcTemplate.execute(q);
			insertImageLog(-1L, "/test/test");
		} catch (Exception e) {
			throw new AssertionError("이미지 테이블 생성 실패");
		}
	}

	@Transactional
	public void insertImageLog(@NonNull Long postId, @NotNull String key) {
		String q = """
			INSERT INTO %s 
			(POST_ID, IMAGE_KEY)
			 VALUES (?,?)
			""".formatted(IMAGE_TABLE_NAME);

		jdbcTemplate.update(q, postId, key);
	}

}
