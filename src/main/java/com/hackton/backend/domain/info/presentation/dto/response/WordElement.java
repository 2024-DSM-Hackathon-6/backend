package com.hackton.backend.domain.info.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WordElement {
    private final Long id;
    private final String title;
    private final String content;
}
