package com.hackton.backend.domain.info.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // jpa proxy
@Entity
public class InfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(300)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(3000)", nullable = false)
    private String content;

    @Column(columnDefinition = "VARCHAR(300)", nullable = false)
    private String categoryName;

    private LocalDateTime createDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "info_category_id", nullable = false)
    private InfoCategoryEntity infoCategory;

    @Builder
    public InfoEntity(String title, String content, String categoryName, InfoCategoryEntity infoCategory) {
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.infoCategory = infoCategory;
    }
}
