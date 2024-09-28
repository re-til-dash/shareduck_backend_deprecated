package com.shareduck.shareduck.domain.board.entity;

import com.shareduck.shareduck.domain.board.enumerate.PostState;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static org.hibernate.type.SqlTypes.JSON;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@SQLDelete(sql = "UPDATE POST SET state = 'DELETED' WHERE id = ?")
public class Post {

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

    @Comment("HTML 형식의 본문")
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @OneToMany(mappedBy = "post", cascade = PERSIST, orphanRemoval = true)
    private final Set<Hashtag> hashtags = new HashSet<>();

    @Comment("프로퍼티 (메타데이탸)")
    @Column(name = "properties", columnDefinition = "json")
    @JdbcTypeCode(JSON)
    private Map<String, Object> properties = new HashMap<>();

    @Comment("썸네일경로")
    private String thumbnailPath;

    @CreationTimestamp
    @Comment("생성일자")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Comment("마지막수정일")
    private LocalDateTime modifiedAt;

    @Comment("상태 (대기중, 활성화, 삭제)")
    @Enumerated(STRING)
    private PostState state = PostState.PENDING;

    public void addTag(@NonNull Hashtag hashtag) {
        this.hashtags.add(hashtag);
        hashtag.setPost(this);
    }

    public void removeTag(@NonNull Hashtag hashtag) {
        this.hashtags.remove(hashtag);
        hashtag.setPost(null);
    }

    @Builder
    private Post(UserEntity user, Category category, String title, String content,
                 Map<String, Object> properties, String thumbnailPath) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.properties = properties;
        this.thumbnailPath = thumbnailPath;
    }

    public void update(String title, String content, Map<String, Object> properties,
                       String thumbnailPath) {
        this.title = title;
        this.content = content;
        this.properties = properties;
        this.thumbnailPath = thumbnailPath;
        this.state = PostState.ACTIVE;
    }
}