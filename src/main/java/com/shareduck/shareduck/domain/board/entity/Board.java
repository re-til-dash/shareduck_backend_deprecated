package com.shareduck.shareduck.domain.board.entity;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import com.shareduck.shareduck.domain.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE board SET deleted = true WHERE id = ?")
public class Board {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Comment("글쓴이아이디")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Comment("카테고리ID")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Comment("제목")
	private String title;

	@Comment("본문")
	@Column(columnDefinition = "TEXT")
	private String content;

	@CreationTimestamp
	@Comment("생성일자")
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "board", cascade = PERSIST, orphanRemoval = true)
	private final Set<Hashtag> hashtags = new HashSet<>();

	@UpdateTimestamp
	@Comment("마지막수정일")
	private LocalDateTime modifiedAt;

	@Comment("삭제여부")
	private boolean deleted;

	public void addTag(@NonNull Hashtag hashtag) {
		this.hashtags.add(hashtag);
		// hashtag.setBoard(this);
	}

	public void removeTags(@NonNull Hashtag hashtag) {
		this.hashtags.remove(hashtag);
		hashtag.setBoard(null);
	}

	static Board create(@NonNull UserEntity user, @NonNull Category category, @NonNull String title,
		@NonNull String content) {
		return Board.builder()
			.user(user)
			.category(category)
			.title(title)
			.content(content)
			.build();
	}

	@Builder
	private Board(UserEntity user, Category category, String title, String content) {
		this.user = user;
		this.category = category;
		this.title = title;
		this.content = content;
	}
}
