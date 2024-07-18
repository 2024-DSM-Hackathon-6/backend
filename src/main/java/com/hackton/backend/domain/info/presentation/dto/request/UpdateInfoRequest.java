package com.hackton.backend.domain.info.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateInfoRequest {

    private String title;
    private String content;
}
