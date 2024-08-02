package com.shareduck.shareduck.domain.board.enumerate;

public enum PostState {
	PENDING, //	 서버에 들어오기만 하고, 사용작가 등록을 하지 않은 상태
	ACTIVE, // 활성화 상태
	DELETED // 삭제 상태
}
