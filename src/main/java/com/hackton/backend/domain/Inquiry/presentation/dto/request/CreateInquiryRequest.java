package com.hackton.backend.domain.Inquiry.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateInquiryRequest {
    private Long feedId;
    private String content;
}
