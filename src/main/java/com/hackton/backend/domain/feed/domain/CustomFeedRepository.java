package com.hackton.backend.domain.feed.domain;

import java.time.LocalDate;
import java.util.List;

public interface CustomFeedRepository {

    List<FeedEntity> findAllOrderByCreateDate();

    List<FeedEntity> findAllOrderByLikeCount();

    List<FeedEntity> findAllByTitle(String title, String accountId, LocalDate date);
}
