package com.hackton.backend.domain.status.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StatusListResponse {

    private final List<StatusElement> statusElements;
}
