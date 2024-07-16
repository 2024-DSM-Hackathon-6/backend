package com.hackton.backend.domain.feed.service;

import com.hackton.backend.domain.feed.domain.FeedEntity;
import com.hackton.backend.domain.feed.domain.FeedLikeEntity;
import com.hackton.backend.domain.feed.domain.FeedLikeRepository;
import com.hackton.backend.domain.feed.domain.FeedRepository;
import com.hackton.backend.domain.user.domain.UserEntity;
import com.hackton.backend.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedLikeService {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final FeedLikeRepository feedLikeRepository;

    @Transactional
    public void createFeedLike(Long feedId, String userIdentifier) {
        FeedEntity feed = feedRepository.findById(feedId)
                .orElseThrow(RuntimeException::new);

        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(RuntimeException::new);

        if (feedLikeRepository.existsByFeedAndUser(feed, user)) {
            throw new RuntimeException(); // if exist like -> throw exception
        }

        feed.addLikeCount();

        feedLikeRepository.save(
                FeedLikeEntity.builder()
                        .feed(feed)
                        .user(user).build()
        );
    }

    @Transactional
    public void deleteFeedLike(Long feedId, String userIdentifier) {
        FeedEntity feed = feedRepository.findById(feedId)
                .orElseThrow(RuntimeException::new);

        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(RuntimeException::new);

        FeedLikeEntity feedLike = feedLikeRepository.findByFeedAndUser(feed, user)
                .orElseThrow(RuntimeException::new);

        feed.minusCount();

        feedLikeRepository.delete(feedLike);
    }
}
