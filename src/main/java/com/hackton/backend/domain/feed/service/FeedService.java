package com.hackton.backend.domain.feed.service;

import com.hackton.backend.domain.feed.domain.FeedEntity;
import com.hackton.backend.domain.feed.domain.FeedRepository;
import com.hackton.backend.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.hackton.backend.domain.feed.presentation.dto.request.UpdateFeedRequest;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedDetailResponse;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedElement;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedListResponse;
import com.hackton.backend.domain.user.domain.UserEntity;
import com.hackton.backend.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public void createFeed(CreateFeedRequest request, String userIdentifier) {
        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(() -> new RuntimeException(""));

        feedRepository.save(
                FeedEntity.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .likeCount(0)
                        .createDate(LocalDateTime.now())
                        .user(user)
                        .build()
        );
    }

    public void deleteFeed(Long feedId, String userIdentifier) {
        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(() -> new RuntimeException(""));

        FeedEntity feed = feedRepository.findById((feedId))
                .orElseThrow(RuntimeException::new);

        if (!feed.getUser().getAccountId().equals(user.getAccountId())) {
            throw new RuntimeException();
        }

        feedRepository.delete(feed);
    }

    @Transactional
    public void modifyFeed(Long feedId, UpdateFeedRequest request, String userIdentifier) {
        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(() -> new RuntimeException(""));

        FeedEntity feed = feedRepository.findById((feedId))
                .orElseThrow(RuntimeException::new);

        if (!feed.getUser().getAccountId().equals(user.getAccountId())) {
            throw new RuntimeException();
        }

        feed.updateFeed(
                request.getTitle(),
                request.getContent()
        );
    }

    public FeedListResponse getFeedListBySort(String sort, String userIdentifier) {
        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(RuntimeException::new);

        List<FeedEntity> feedEntities = switch (sort) {
            case "DATE" -> feedRepository.findAllOrderByCreateDate();
            case "POPULAR" -> feedRepository.findAllOrderByLikeCount();
            default -> throw new IllegalStateException("Unexpected value: " + sort);
        };

        List<FeedElement> feedElements = feedEntities.stream()
                .map(feed ->
                        FeedElement.builder()
                                .id(feed.getId())
                                .title(feed.getTitle())
                                .content(feed.getContent())
                                .userName(feed.getUser().getAccountId())
                                .likeCount(feed.getLikeCount())
                                .isMine(feed.getUser().getAccountId().equals(user.getAccountId()))
                                .build())
                .toList();

        return new FeedListResponse(feedElements);
    }

    public FeedListResponse getFeedListByTitle(String title, String userIdentifier) {
        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(RuntimeException::new);

        List<FeedElement> feedElements = feedRepository.findAllByTitleContainsOrderByCreateDateDesc(title)
                .stream()
                .map(feed ->
                        FeedElement.builder()
                                .id(feed.getId())
                                .title(feed.getTitle())
                                .content(feed.getContent())
                                .userName(feed.getUser().getAccountId())
                                .likeCount(feed.getLikeCount())
                                .isMine(feed.getUser().getAccountId().equals(user.getAccountId()))
                                .build())
                .toList();

        return new FeedListResponse(feedElements);
    }

    /*public FeedListResponse getFeedListByFeedFilter(
            String title,
            String accountId,
            LocalDate date,
            String status,
            String userIdentifier
    ) {
        FeedFilter feedFilter = new FeedFilter(title, accountId, date, status);

    }*/
}
