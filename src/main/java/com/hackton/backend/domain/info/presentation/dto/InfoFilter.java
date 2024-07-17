package com.hackton.backend.domain.info.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InfoFilter {
    private String title;
    private LocalDate date;
}
