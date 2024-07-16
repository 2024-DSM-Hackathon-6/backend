package com.hackton.backend.domain.feed.domain;

import com.hackton.backend.domain.user.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FeedLikeRepository extends CrudRepository<FeedLikeEntity, Long> {

    boolean existsByFeedAndUser(FeedEntity feed, UserEntity user);

    Optional<FeedLikeEntity> findByFeedAndUser(FeedEntity feed, UserEntity user);
}
