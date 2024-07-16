package com.hackton.backend.infra.excel;

import com.hackton.backend.domain.info.domain.InfoCategoryRepository;
import com.hackton.backend.domain.info.domain.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ExcelService {

    private final InfoRepository infoRepository;
    private final InfoCategoryRepository infoCategoryRepository;
}
