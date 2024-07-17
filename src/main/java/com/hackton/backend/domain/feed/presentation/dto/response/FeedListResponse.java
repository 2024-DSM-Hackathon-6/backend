package com.hackton.backend.domain.feed.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedListResponse {

    private final List<FeedElement> feedElements;
}
