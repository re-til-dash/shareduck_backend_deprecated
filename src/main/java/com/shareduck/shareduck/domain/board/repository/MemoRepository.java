package com.shareduck.shareduck.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shareduck.shareduck.domain.board.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
	Page<Memo> findByCategoryIdOrderByIdDesc(long categoryId, Pageable pageable);

	@Query("SELECT m FROM Memo m WHERE m.category.id = :categoryId AND m.content LIKE %:keyword%")
	Page<Memo> findByCategoryIdAndKeyword(@Param("categoryId") Long categoryId, @Param("keyword") String keyword,
		Pageable pageable);

}
