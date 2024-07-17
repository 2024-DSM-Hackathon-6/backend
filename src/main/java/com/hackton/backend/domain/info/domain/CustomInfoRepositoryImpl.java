package com.hackton.backend.domain.info.domain;

import com.hackton.backend.domain.info.presentation.dto.InfoFilter;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.hackton.backend.domain.feed.domain.QFeedEntity.feedEntity;
import static com.hackton.backend.domain.info.domain.QInfoEntity.infoEntity;

@RequiredArgsConstructor
@Component
public class CustomInfoRepositoryImpl implements CustomInfoRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<InfoEntity> findAllByInfoFilter(InfoFilter infoFilter) {
        return queryFactory
                .selectFrom(infoEntity)
                .where(
                        containsTitle(infoFilter.getTitle()),
                        eqDate(infoFilter.getDate())
                )
                .fetch();
    }

    // dynamic query conditions

    private BooleanExpression containsTitle(String title) {
        return title == null ? null : feedEntity.title.like(title);
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
