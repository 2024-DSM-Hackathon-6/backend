package com.hackton.backend.domain.info.presentation;

import com.hackton.backend.domain.info.presentation.dto.response.WordDetailResponse;
import com.hackton.backend.domain.info.presentation.dto.response.WordListResponse;
import com.hackton.backend.domain.info.service.InfoService;
import com.hackton.backend.infra.excel.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/infos")
@RestController
public class InfoController {

    private final ExcelService excelService;
    private final InfoService infoService;

    @PostMapping("/save")
    public void excelParsing() {
        excelService.excelParsing();
    }

    @GetMapping
    public WordListResponse getWordsByCategoryName(@RequestParam("name") String categoryName) {
        return infoService.getWordsByCategoryName(categoryName);
    }

    @GetMapping("/detail/{info-id}")
    public WordDetailResponse getWordDetailById(@PathVariable("info-id") Long infoId) {
        return infoService.getWordDetailById(infoId);
    }
}
