package com.hackton.backend.domain.status.presentation;

import com.hackton.backend.domain.status.presentation.dto.request.CreateFixRequest;
import com.hackton.backend.domain.status.presentation.dto.request.CreateInquiryRequest;
import com.hackton.backend.domain.status.presentation.dto.response.StatusDetailResponse;
import com.hackton.backend.domain.status.presentation.dto.response.StatusListResponse;
import com.hackton.backend.domain.status.service.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/status")
@RestController
public class StatusController {

    private final StatusService statusService;

    @Operation(summary = "문의하기")
    @PostMapping("/inquiry")
    public void createInquiry(@RequestBody CreateInquiryRequest request, @RequestHeader("X-identifier") String identifier) {
        statusService.createInquiry(request, identifier);
    }

    @Operation(summary = "수정 요청하기")
    @PostMapping("/fix")
    public void createFixRequest(@RequestBody CreateFixRequest request, @RequestHeader("X-identifier") String identifier) {
        statusService.createFixRequest(request, identifier);
    }

    @Operation(summary = "웹용 정보 글 관련 문의, 수정 요청 리스트 조회")
    @GetMapping("/list/{info-id}")
    public StatusListResponse getStatusListByInfoIdAndStatus(
            @PathVariable("info-id") Long infoId,
            @RequestParam("status") String status
    ) {
        return statusService.getStatusListByInfoIdAndStatus(infoId, status);
    }

    @Operation(summary = "웹용 정보 글 관련 문의, 수정 요청 상세 조회")
    @GetMapping("/detail/{status-id}")
    public StatusDetailResponse getStatusDetailById(
            @PathVariable("status-id") Long statusId
    ) {
        return statusService.getStatusDetailById(statusId);
    }
}
