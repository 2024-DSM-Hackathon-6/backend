package com.hackton.backend.domain.status.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateFixRequest {
    private Long infoId;
    private String content;
}
