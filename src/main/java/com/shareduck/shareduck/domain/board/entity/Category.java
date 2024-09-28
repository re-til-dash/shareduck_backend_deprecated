package com.shareduck.shareduck.domain.board.entity;

import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
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

    @Comment("카테고리아이콘")
    private String categoryIcon;

    @Column(name = "properties", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> properties = new HashMap<>();

    public static Category create(UserEntity user, @NonNull String name, String categoryIcon, Map<String, Object> properties) {
        Category category = new Category();
        category.user = user;
        category.setName(name);
        category.categoryIcon = categoryIcon;
        category.properties = properties;
        return category;
    }

    public void updateName(@NonNull String name) {
        setName(name);
    }

    public void updateProperties(@NonNull Map<String, Object> properties) {
        this.properties = properties;
    }

    private void setName(String name) {
        this.name = name.trim();
    }

}
