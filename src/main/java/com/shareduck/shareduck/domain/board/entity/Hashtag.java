package com.shareduck.shareduck.domain.board.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.util.Objects;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)

public class Hashtag {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Comment("글번호")
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "post_id")
	@Setter
	private Post post;

	@Comment("태그명")
	private String tag;

	public static Hashtag create(@NonNull String tag) {
		Hashtag newHashtag = new Hashtag();
		newHashtag.tag = tag;
		return newHashtag;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Hashtag hashtag = (Hashtag)o;
		return Objects.equals(tag, hashtag.tag);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tag);
	}
}
