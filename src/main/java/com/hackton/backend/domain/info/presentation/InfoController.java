package com.hackton.backend.domain.info.presentation;

import com.hackton.backend.infra.excel.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/infos")
@RestController
public class InfoController {

    private final ExcelService excelService;

    @PostMapping("/save")
    public void excelParsing() {
        excelService.excelParsing();
    }
}
