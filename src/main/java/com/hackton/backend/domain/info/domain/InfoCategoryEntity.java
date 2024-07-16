package com.hackton.backend.domain.info.domain;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class InfoCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Builder
    public InfoCategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 임시 처리
    public static InfoCategoryEntity getDefaultCategory() {
        return InfoCategoryEntity.builder()
                .id(5L)
                .name("기본")
                .build();
    }
}
