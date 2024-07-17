package com.hackton.backend.domain.feed.domain;

import com.hackton.backend.domain.feed.presentation.dto.FeedFilter;
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
    public List<FeedEntity> findAllByFeedFilter(FeedFilter feedFilter) {
        return queryFactory
                .selectFrom(feedEntity)
                .join(userEntity)
                .on(feedEntity.user.id.eq(userEntity.id))
                .where(
                        containsTitle(feedFilter.getTitle()),
                        eqUserName(feedFilter.getAccountId()),
                        eqDate(feedFilter.getDate())
                )
                .fetch();
    }

    // dynamic query conditions

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
