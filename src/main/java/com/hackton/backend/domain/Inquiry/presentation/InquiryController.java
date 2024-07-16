package com.hackton.backend.domain.Inquiry.presentation;

import com.hackton.backend.domain.Inquiry.presentation.dto.request.CreateInquiryRequest;
import com.hackton.backend.domain.Inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/inquiries")
@RestController
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    public void createInquiry(@RequestBody CreateInquiryRequest request, @RequestHeader("X-identifier") String identifier) {
        inquiryService.createInquiry(request, identifier);
    }
}
