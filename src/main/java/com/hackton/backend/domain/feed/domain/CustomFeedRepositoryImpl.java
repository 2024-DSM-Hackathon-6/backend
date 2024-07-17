package com.hackton.backend.domain.feed.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.hackton.backend.domain.feed.domain.QFeedEntity.feedEntity;

@RequiredArgsConstructor
@Component
public class CustomFeedRepositoryImpl implements CustomFeedRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<FeedEntity> findAllOrderByCreateDate() {
        return queryFactory
                .selectFrom(feedEntity)
                .orderBy(feedEntity.createDate.desc())
                .fetch();
    }

    @Override
    public List<FeedEntity> findAllOrderByLikeCount() {
        return queryFactory
                .selectFrom(feedEntity)
                .orderBy(feedEntity.likeCount.desc())
                .fetch();
    }
}
