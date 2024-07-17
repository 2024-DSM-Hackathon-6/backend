package com.hackton.backend.domain.status.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class StatusElement {

    private final Long id;
    private final LocalDate createDate;
    private final String userName;
    private final String name;
}
