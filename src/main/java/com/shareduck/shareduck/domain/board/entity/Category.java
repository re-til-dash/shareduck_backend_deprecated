package com.shareduck.shareduck.domain.board.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import org.hibernate.annotations.Comment;

import com.shareduck.shareduck.domain.user.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Comment("카테고리 만든 유저 ID")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Comment("카테고리명")
	private String name;

	public static Category create(UserEntity user, @NonNull String name) {
		Category category = new Category();
		category.user = user;
		category.setName(name);
		return category;
	}

	void changeName(@NonNull String name) {
		setName(name);
	}

	private void setName(String name) {
		this.name = name.trim();

	}

}
