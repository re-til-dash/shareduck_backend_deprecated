package com.shareduck.shareduck.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

}
