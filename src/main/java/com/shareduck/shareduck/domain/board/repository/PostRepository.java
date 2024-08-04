package com.shareduck.shareduck.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shareduck.shareduck.domain.board.entity.Post;

import io.lettuce.core.dynamic.annotation.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("""
			select p from Post p 
			where p.category.id = :categoryId AND p.state = 'ACTIVE' 
			order by p.id DESC 
		""")
	Page<Post> findActivePostByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
}
