package com.shareduck.shareduck.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareduck.shareduck.domain.board.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
