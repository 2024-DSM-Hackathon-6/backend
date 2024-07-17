package com.hackton.backend.domain.feed.domain;

import java.util.List;

public interface CustomFeedRepository {

    List<FeedEntity> findAllOrderByCreateDate();

    List<FeedEntity> findAllOrderByLikeCount();

    List<FeedEntity> findAllByTitleContainsOrderByCreateDateDesc(String title);
}
