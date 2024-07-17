package com.hackton.backend.domain.feed.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedRepository extends CrudRepository<FeedEntity, Long>, CustomFeedRepository {

    List<FeedEntity> findAllByTitleContainsOrderByCreateDateDesc(String title);
}
