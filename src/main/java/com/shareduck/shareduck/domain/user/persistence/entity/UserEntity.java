package com.shareduck.shareduck.domain.user.persistence.entity;

import com.shareduck.shareduck.common.audit.BaseEntity;
import com.shareduck.shareduck.domain.user.persistence.enums.UserRole;
import com.shareduck.shareduck.domain.user.persistence.enums.UserState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 100)
    private Long id;
    private String email;
    private String password;
    private UserRole role;
    private String profile;
    private String nickname;
    private String phone;
    private UserState state;
    private LocalDate deleteDate;
    private LocalDateTime lastConnect;



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
}
