package com.hackton.backend.domain.info.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WordListResponse {
    private final List<WordElement> wordElements;
}
