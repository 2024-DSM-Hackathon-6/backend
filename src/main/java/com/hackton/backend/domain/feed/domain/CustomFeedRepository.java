package com.hackton.backend.domain.feed.domain;

import com.hackton.backend.domain.feed.presentation.dto.FeedFilter;

import java.util.List;

public interface CustomFeedRepository {

    List<FeedEntity> findAllOrderByCreateDate();

    List<FeedEntity> findAllOrderByLikeCount();

    List<FeedEntity> findAllByFeedFilter(FeedFilter feedFilter);

    List<FeedEntity> findAllByTitleContainsOrderByCreateDateDesc(String title);
}
