package com.hackton.backend.domain.feed.presentation;

import com.hackton.backend.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedDetailResponse;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedListResponse;
import com.hackton.backend.domain.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/detail/{feed-id}")
    public FeedDetailResponse getFeedDetailById(
            @PathVariable("feed-id") Long feedId,
            @RequestHeader("X-identifier") String identifier
    ) {
        return feedService.getFeedDetailById(feedId, identifier);
    }

    @PostMapping
    public void createFeed(
            @RequestBody CreateFeedRequest request,
            @RequestHeader("X-identifier") String identifier
    ) {
        feedService.createFeed(request, identifier);
    }

    @DeleteMapping("/delete/{feed-id}")
    public void deleteFeed(
            @PathVariable(name = "feed-id") Long feedId,
            @RequestHeader("X-identifier") String identifier
    ) {
        feedService.deleteFeed(feedId, identifier);
    }

    @PatchMapping
    public void modifyFeed(
            @PathVariable(name = "feed-id") Long feedId,
            @RequestHeader("X-identifier") String identifier
    ) {
        feedService.modifyFeed(feedId, identifier);
    }

    @GetMapping("/app")
    public FeedListResponse getFeedListBySort(
            @RequestParam("sort") String sort,
            @RequestHeader("X-identifier") String identifier
    ) {
        return feedService.getFeedListBySort(sort, identifier);
    }

    @GetMapping("/web")
    public void getFeedListByFeedFilter(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "accountId", required = false) String accountId,
            @RequestParam(value = "date", required = false) LocalDate date,
            @RequestParam(value = "status", required = false) String status,
            @RequestHeader("X-identifier") String identifier
    ) {
//        return feedService.getFeedListByFeedFilter(title, accountId, date, status, identifier);
    }
}
