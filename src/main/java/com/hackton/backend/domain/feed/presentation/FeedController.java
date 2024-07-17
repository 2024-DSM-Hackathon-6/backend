package com.hackton.backend.domain.feed.presentation;

import com.hackton.backend.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.hackton.backend.domain.feed.presentation.dto.request.UpdateFeedRequest;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedDetailResponse;
import com.hackton.backend.domain.feed.presentation.dto.response.FeedListResponse;
import com.hackton.backend.domain.feed.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
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

    @DeleteMapping("/{feed-id}")
    public void deleteFeed(
            @PathVariable(name = "feed-id") Long feedId,
            @RequestHeader("X-identifier") String identifier
    ) {
        feedService.deleteFeed(feedId, identifier);
    }

    @PatchMapping("/{feed-id}")
    public void modifyFeed(
            @PathVariable(name = "feed-id") Long feedId,
            @RequestBody UpdateFeedRequest request,
            @RequestHeader("X-identifier") String identifier
    ) {
        feedService.modifyFeed(feedId, request, identifier);
    }

    @Operation(summary = "앱 용 게시글 리스트 조회", description = "DATE(최신순), POPULAR(인기순)")
    @GetMapping("/app")
    public FeedListResponse getFeedListBySort(
            @RequestParam("sort") String sort,
            @RequestHeader("X-identifier") String identifier
    ) {
        return feedService.getFeedListBySort(sort, identifier);
    }

    @Operation(summary = "웹 용 제목 검색으로 게시글 조회")
    @GetMapping("/search")
    public FeedListResponse getFeedListByTitle(
            @RequestParam("title") String title,
            @RequestHeader("X-identifier") String identifier
    ) {
        return feedService.getFeedListByTitle(title, identifier);
    }
}
