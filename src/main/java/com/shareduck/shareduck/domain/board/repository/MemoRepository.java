package com.shareduck.shareduck.domain.board.repository;

import com.shareduck.shareduck.domain.board.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemoRepository extends JpaRepository<Memo, Long>, MemoRepositoryCustom {
    @Deprecated
    Page<Memo> findByCategoryIdOrderByIdDesc(long categoryId, Pageable pageable);

    @Deprecated
    @Query("SELECT m FROM Memo m WHERE m.category.id = :categoryId AND m.content LIKE %:keyword%")
    Page<Memo> findByCategoryIdAndKeyword(@Param("categoryId") Long categoryId, @Param("keyword") String keyword,
                                          Pageable pageable);

}
