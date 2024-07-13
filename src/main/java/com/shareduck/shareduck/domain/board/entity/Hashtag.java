package com.shareduck.shareduck.domain.board.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hashtag {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Comment("글번호")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "board_id")
	@Setter
	@EqualsAndHashCode.Include
	private Board board;

	@EqualsAndHashCode.Include
	@Comment("태그명")
	private String tag;

	static Hashtag from(@NonNull String tag, @NonNull Board board) {
		Hashtag newHashtag = new Hashtag();
		newHashtag.tag = tag;
		newHashtag.board = board;
		return newHashtag;
	}

}
