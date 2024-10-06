package com.shareduck.shareduck.domain.board.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.board.entity.Memo;
import com.shareduck.shareduck.domain.board.request.MemoSearchConditions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.shareduck.shareduck.domain.board.entity.QMemo.memo;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class MemoRepositoryImpl implements MemoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Memo> findMemoBySearchCondition(CurrentUser currentUser, MemoSearchConditions searchConditions, Pageable pageable) {
        List<Memo> content = queryFactory
                .selectFrom(memo)
                .where(
                        memo.user.id.eq(currentUser.getUserId()),
                        categoryIdEq(searchConditions.categoryId()),
                        keywordEq(searchConditions.keyword())
                )
                .orderBy(memo.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(memo.count())
                .where(
                        memo.user.id.eq(currentUser.getUserId()),
                        categoryIdEq(searchConditions.categoryId()),
                        keywordEq(searchConditions.keyword())
                );
        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);


    }


    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId == null ? null : memo.category.id.eq(categoryId);
    }

    private BooleanExpression keywordEq(String keyword) {
        return !hasText(keyword) ? null : memo.content.contains(keyword);
    }
}
