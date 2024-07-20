package com.shareduck.shareduck.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shareduck.shareduck.domain.board.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Page<Post> findPostByCategoryIdOrderByIdDesc(Long categoryId, Pageable pageable);
}
