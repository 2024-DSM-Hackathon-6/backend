package com.hackton.backend.domain.info.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InfoStatusElement {
    private final Long id;
    private final String title;
    private final String content;
    private final List<String> statusName;
}
