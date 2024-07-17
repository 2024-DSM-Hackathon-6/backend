package com.hackton.backend.domain.info.domain;

import com.hackton.backend.domain.info.presentation.dto.InfoFilter;

import java.util.List;

public interface CustomInfoRepository {

    List<InfoEntity> findAllByInfoFilter(InfoFilter infoFilter);
}
