package com.hackton.backend.domain.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // jpa proxy
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(320)", nullable = false, unique = true)
    private String accountId;

    @Column(columnDefinition = "CHAR(60)", nullable = false)
    private String password;

    @Builder
    public UserEntity(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }
}
