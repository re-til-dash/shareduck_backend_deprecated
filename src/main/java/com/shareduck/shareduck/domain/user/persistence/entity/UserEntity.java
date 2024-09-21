package com.shareduck.shareduck.domain.user.persistence.entity;

import com.shareduck.shareduck.common.audit.BaseEntity;
import com.shareduck.shareduck.domain.user.persistence.enums.ProviderEnum;
import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import com.shareduck.shareduck.domain.user.persistence.enums.UserState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 100)
    private Long id;
    @Comment("로그인 사용 ID")
    private String email;
    @Comment("Password")
    private String password;
    @Comment("사용자 등급 정보 [USER]")
    private UserRole role;
    @Comment("프로필 path")
    private String profile;
    @Comment("이름")
    private String name;
    @Comment("Nickname")
    private String nickname;
    @Comment("사용자 생성 시 제공하는 UUID")
    private String idx;
    @Comment("연락처")
    private String phone;
    @Comment("사용자 상태 정보")
    private UserState state;
    @Comment("삭제 일자")
    private LocalDate deleteDate;
    @Comment("마지막 접속 일자")
    private LocalDateTime lastConnect;

    @Enumerated(EnumType.STRING)
    private ProviderEnum provider;

    public UserEntity addPassword(String password) {
        this.password = password;
        return this;
    }

    public void addNickname(String nickname) {
        this.nickname = nickname;
    }

    public void delete(){
        this.state = UserState.DELETE;
        this.deleteDate = LocalDate.now();
    }

    //TODO : Entity 설계 완성 후 추가 설계 필수
    public UserEntity update(UserEntity request) {
        // 이메일, 비밀번호, UUID는 변경하지 않음
        Optional.ofNullable(request.name).ifPresent(e -> this.name = e);
        Optional.ofNullable(request.nickname).ifPresent(e -> this.nickname = e);
        Optional.ofNullable(request.profile).ifPresent(e -> this.profile = e);
        Optional.ofNullable(request.phone).ifPresent(e -> this.phone = e);
        Optional.ofNullable(request.state).ifPresent(e -> this.state = e);
        Optional.ofNullable(request.role).ifPresent(e -> this.role = e);
        Optional.ofNullable(request.lastConnect).ifPresent(e -> this.lastConnect = e);
        Optional.ofNullable(request.deleteDate).ifPresent(e -> this.deleteDate = e);
        return this;
    }

    public UserEntity oauth(UserEntity request) {
        update(request);
        Optional.ofNullable(request.provider).ifPresent(e -> this.provider = e);
        Optional.ofNullable(request.email).ifPresent(e -> this.email = e);
        return this;
    }
}
