package com.hackton.backend.domain.status.service;

import com.hackton.backend.domain.feed.domain.FeedEntity;
import com.hackton.backend.domain.feed.domain.FeedRepository;
import com.hackton.backend.domain.status.domain.StatusEntity;
import com.hackton.backend.domain.status.domain.StatusRepository;
import com.hackton.backend.domain.status.presentation.dto.request.CreateFixRequest;
import com.hackton.backend.domain.status.presentation.dto.request.CreateInquiryRequest;
import com.hackton.backend.domain.status.presentation.dto.response.StatusDetailResponse;
import com.hackton.backend.domain.status.presentation.dto.response.StatusElement;
import com.hackton.backend.domain.status.presentation.dto.response.StatusListResponse;
import com.hackton.backend.domain.user.domain.UserEntity;
import com.hackton.backend.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusService {

    private final StatusRepository statusRepository;
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    public void createInquiry(CreateInquiryRequest request, String userIdentifier) {
        FeedEntity feed = feedRepository.findById(request.getFeedId())
                .orElseThrow(RuntimeException::new);

        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(RuntimeException::new);

        statusRepository.save(
                StatusEntity.builder()
                        .feed(feed)
                        .user(user)
                        .content(request.getContent())
                        .name("문의")
                        .build()
        );
    }

    public void createFixRequest(CreateFixRequest request, String userIdentifier) {
        FeedEntity feed = feedRepository.findById(request.getFeedId())
                .orElseThrow(RuntimeException::new);

        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(RuntimeException::new);

        statusRepository.save(
                StatusEntity.builder()
                        .feed(feed)
                        .user(user)
                        .content(request.getContent())
                        .name("수정 요청")
                        .build()
        );
    }

    public StatusListResponse getStatusListByFeedIdAndStatus(Long feedId, String status) {
        List<StatusEntity> statusEntities = switch (status) {
            case "INQUIRY" -> statusRepository.findAllByFeedIdAndName(feedId, "문의");
            case "FIX" -> statusRepository.findAllByFeedIdAndName(feedId, "수정 요청");
            default -> statusRepository.findAllByFeedId(feedId);
        };

        List<StatusElement> statusElements = statusEntities.stream()
                .map(s -> new StatusElement(
                        s.getId(),
                        s.getCreateDate().toLocalDate(),
                        s.getUser().getAccountId(),
                        s.getName()
                ))
                .toList();

        return new StatusListResponse(statusElements);
    }

    public StatusDetailResponse getStatusDetailById(Long statusId) {
        StatusEntity status = statusRepository.findById(statusId)
                .orElseThrow(RuntimeException::new);

        return new StatusDetailResponse(
                status.getId(),
                status.getName(),
                status.getContent()
        );
    }
}
