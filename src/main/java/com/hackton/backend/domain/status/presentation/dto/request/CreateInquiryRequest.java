package com.hackton.backend.domain.status.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateInquiryRequest {
    private Long infoId;
    private String content;
}
