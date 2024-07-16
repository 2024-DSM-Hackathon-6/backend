package com.hackton.backend.domain.Inquiry.service;

import com.hackton.backend.domain.Inquiry.domain.InquiryEntity;
import com.hackton.backend.domain.Inquiry.domain.InquiryRepository;
import com.hackton.backend.domain.Inquiry.presentation.dto.request.CreateInquiryRequest;
import com.hackton.backend.domain.feed.domain.FeedEntity;
import com.hackton.backend.domain.feed.domain.FeedRepository;
import com.hackton.backend.domain.user.domain.UserEntity;
import com.hackton.backend.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;

    public void createInquiry(CreateInquiryRequest request, String userIdentifier) {
        FeedEntity feed = feedRepository.findById(request.getFeedId())
                .orElseThrow(RuntimeException::new);

        UserEntity user = userRepository.findByIdentifier(userIdentifier)
                .orElseThrow(RuntimeException::new);

        inquiryRepository.save(
                InquiryEntity.builder()
                        .feed(feed)
                        .user(user)
                        .content(request.getContent())
                        .build()
        );
    }
}
