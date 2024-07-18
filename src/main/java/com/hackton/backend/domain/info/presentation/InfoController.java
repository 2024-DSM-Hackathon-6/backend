package com.hackton.backend.domain.info.presentation;

import com.hackton.backend.domain.info.presentation.dto.request.UpdateInfoRequest;
import com.hackton.backend.domain.info.presentation.dto.response.InfoStatusListResponse;
import com.hackton.backend.domain.info.presentation.dto.response.WordDetailResponse;
import com.hackton.backend.domain.info.presentation.dto.response.WordListResponse;
import com.hackton.backend.domain.info.service.InfoService;
import com.hackton.backend.infra.excel.ExcelService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/infos")
@RestController
public class InfoController {

    private final ExcelService excelService;
    private final InfoService infoService;

    @Operation(summary = "엑셀 저장(호출하지 않아도 됨)")
    @PostMapping("/save")
    public void excelParsing() {
        excelService.excelParsing();
    }

    @Operation(summary = "용어 사전 리스트 조회(카테고리 이름으로 필터링 { 경제, 사회, 금융, 공공 })")
    @GetMapping
    public WordListResponse getWordsByCategoryName(@RequestParam("name") String categoryName) {
        return infoService.getWordsByCategoryName(categoryName);
    }

    @Operation(summary = "용어 사전 상세 조회")
    @GetMapping("/detail/{info-id}")
    public WordDetailResponse getWordDetailById(@PathVariable("info-id") Long infoId) {
        return infoService.getWordDetailById(infoId);
    }

    @Operation(summary = "웹 용 정보 리스트 조회")
    @GetMapping("/web")
    public InfoStatusListResponse getFeedListByFeedFilter(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "date", required = false) LocalDate date
    ) {
        return infoService.getInfoListByInfoFilter(title, date);
    }

    @Operation(summary = "정보 글 삭제")
    @DeleteMapping("/{info-id}")
    public void deleteInfo(
            @PathVariable("info-id") Long infoId
    ) {
        infoService.deleteInfo(infoId);
    }

    @Operation(summary = "정보 글 수정")
    @PatchMapping("/{info-id}")
    public void updateInfo(
            @PathVariable("info-id") Long infoId,
            @RequestBody UpdateInfoRequest request
    ) {
        infoService.updateInfo(infoId, request);
    }
}
