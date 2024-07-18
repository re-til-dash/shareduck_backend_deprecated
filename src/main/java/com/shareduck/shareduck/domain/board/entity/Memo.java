package com.shareduck.shareduck.domain.board.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.shareduck.shareduck.domain.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Memo {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Comment("메모 만든 유저 ID")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Comment("카테고리 ID")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Comment("내용")
	@Column(columnDefinition = "TEXT")
	private String content;

	@CreationTimestamp
	@Comment("생성일자")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Comment("마지막수정일")
	private LocalDateTime modifiedAt;

	public void changeContent(@NonNull String content) {
		this.content = content;
	}

	public static Memo from(@NonNull UserEntity user, @NonNull Category category, @NonNull String content) {
		Memo memo = new Memo();
		memo.user = user;
		memo.category = category;
		memo.content = content;
		return memo;
	}

}
