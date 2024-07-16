package com.hackton.backend.domain.feed.domain;

import com.hackton.backend.domain.info.domain.InfoCategoryEntity;
import com.hackton.backend.domain.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedEntity feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Builder
    public FeedLikeEntity(FeedEntity feed, UserEntity user) {
        this.feed = feed;
        this.user = user;
    }
}
