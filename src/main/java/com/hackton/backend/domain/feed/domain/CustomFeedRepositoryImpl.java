package com.hackton.backend.domain.feed.domain;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.hackton.backend.domain.feed.domain.QFeedEntity.feedEntity;
import static com.hackton.backend.domain.user.domain.QUserEntity.userEntity;

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

    @Override
    public List<FeedEntity> findAllByTitle(String title, String accountId, LocalDate date) {
        return queryFactory
                .selectFrom(feedEntity)
                .where(
                        containsTitle(title),
                        eqDate(date),
                        eqUserName(accountId)
                )
                .orderBy(feedEntity.createDate.desc())
                .fetch();
    }

    private BooleanExpression containsTitle(String title) {
        return title == null ? null : feedEntity.title.like(title);
    }

    private BooleanExpression eqUserName(String accountId) {
        return accountId == null ? null : userEntity.accountId.eq(accountId);
    }

    private BooleanExpression eqDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        return feedEntity.createDate.year().eq(date.getYear())
                .and(feedEntity.createDate.month().eq(date.getMonthValue()))
                .and(feedEntity.createDate.dayOfMonth().eq(date.getDayOfMonth()));
    }
}
