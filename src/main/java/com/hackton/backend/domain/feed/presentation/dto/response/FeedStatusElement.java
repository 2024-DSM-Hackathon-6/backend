package com.hackton.backend.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class FeedStatusElement {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDate createDate;
    private final String userName;
    private final Integer likeCount;
    private final List<String> statusName;
}
