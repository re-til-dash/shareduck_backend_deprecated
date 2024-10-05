package com.shareduck.shareduck.domain.board.repository;

import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.domain.board.entity.Memo;
import com.shareduck.shareduck.domain.board.request.MemoSearchConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemoRepositoryCustom {

    Page<Memo> findMemoBySearchCondition(CurrentUser user, MemoSearchConditions searchConditions, Pageable pageable);

}
