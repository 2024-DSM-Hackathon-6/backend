package com.hackton.backend.domain.feed.service;

import com.hackton.backend.domain.feed.domain.FeedEntity;
import com.hackton.backend.domain.feed.domain.FeedRepository;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedDetailResponse;
import com.hackton.backend.domain.user.domain.UserEntity;
import com.hackton.backend.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    public FeedDetailResponse getFeedDetailById(Long feedId, String userIdentifier) {
        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(RuntimeException::new);

        FeedEntity feed = feedRepository.findById(feedId)
                .orElseThrow(RuntimeException::new);

        return FeedDetailResponse.builder()
                .title(feed.getTitle())
                .content(feed.getContent())
                .createDate(feed.getCreateDate().toLocalDate())
                .userName(feed.getUser().getAccountId())
                .likeCount(feed.getLikeCount())
                .isMine(feed.getUser().getAccountId().equals(user.getAccountId()))
                .build();
    }
}
