package com.hackton.backend.domain.feed.presentation;

import com.hackton.backend.domain.feed.service.FeedLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class FeedLikeController {

    private final FeedLikeService feedLikeService;

    @PostMapping("/{feed-id}")
    public void createFeedLike(
            @PathVariable("feed-id") Long feedId,
            @RequestHeader("X-identifier") String identifier
    ) {
        feedLikeService.createFeedLike(feedId, identifier);
    }

    @DeleteMapping("/{feed-id}")
    public void deleteFeedLike(
            @PathVariable("feed-id") Long feedId,
            @RequestHeader("X-identifier") String identifier
    ) {
        feedLikeService.deleteFeedLike(feedId, identifier);
    }
}
