package com.hackton.backend.domain.feed.presentation;

import com.hackton.backend.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedDetailResponse;
import com.hackton.backend.domain.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    public void deleteFeed() {

    }

    @PatchMapping
    public void modifyFeed() {

    }
}
