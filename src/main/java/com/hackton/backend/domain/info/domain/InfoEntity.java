package com.hackton.backend.domain.info.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // jpa proxy
@Entity
public class InfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(3000)", nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "info_category_id", nullable = false)
    private InfoCategoryEntity infoCategory;

    @Builder
    public InfoEntity(String title, String content, InfoCategoryEntity infoCategory) {
        this.title = title;
        this.content = content;
        this.infoCategory = infoCategory;
    }
}
