package com.hackton.backend.domain.feed.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FeedFilter {
    private String title;
    private String accountId;
    private LocalDate date;
}
