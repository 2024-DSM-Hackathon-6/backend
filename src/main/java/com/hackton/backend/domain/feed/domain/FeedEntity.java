package com.hackton.backend.domain.feed.domain;

import com.hackton.backend.domain.user.domain.UserEntity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(3000)", nullable = false)
    private String content;

    private LocalDateTime createDate = LocalDateTime.now();

    @Column(columnDefinition = "INT", nullable = false)
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Builder
    public FeedEntity(String title, String content, LocalDateTime createDate, Integer likeCount, UserEntity user) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.likeCount = likeCount;
        this.user = user;
    }

    public void addLikeCount() {
        this.likeCount += 1;
    }

    public void minusCount() {
        this.likeCount -= 1;
    }
}
