package com.shareduck.shareduck.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareduck.shareduck.domain.board.entity.Hashtag;

public interface TagRepository extends JpaRepository<Hashtag, Long> {
}
