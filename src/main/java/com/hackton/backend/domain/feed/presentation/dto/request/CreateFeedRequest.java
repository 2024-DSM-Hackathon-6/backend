package com.hackton.backend.domain.feed.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateFeedRequest {
    private String title;
    private String content;
}
