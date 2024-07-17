package com.hackton.backend.domain.info.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InfoStatusListResponse {
    private final List<InfoStatusElement> infoStatusElements;
}
