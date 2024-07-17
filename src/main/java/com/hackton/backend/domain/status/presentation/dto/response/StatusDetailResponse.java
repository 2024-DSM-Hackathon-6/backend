package com.hackton.backend.domain.status.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatusDetailResponse {

    private final Long id;
    private final String name;
    private final String content;
}
