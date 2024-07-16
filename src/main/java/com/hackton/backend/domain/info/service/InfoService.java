package com.hackton.backend.domain.info.service;

import com.hackton.backend.domain.info.domain.InfoEntity;
import com.hackton.backend.domain.info.domain.InfoRepository;
import com.hackton.backend.domain.info.presentation.dto.response.WordDetailResponse;
import com.hackton.backend.domain.info.presentation.dto.response.WordElement;
import com.hackton.backend.domain.info.presentation.dto.response.WordListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoService {

    private final InfoRepository infoRepository;

    public WordListResponse getWordsByCategoryName(String categoryName) {
        List<WordElement> wordElements = infoRepository.findAllByCategoryName(categoryName)
                .stream()
                .map(word -> new WordElement(
                        word.getId(),
                        word.getTitle(),
                        word.getContent())
                )
                .limit(10)
                .toList();

        return new WordListResponse(wordElements);
    }

    public WordDetailResponse getWordDetailById(Long infoId) {
        InfoEntity info = infoRepository.findById(infoId)
                .orElseThrow(RuntimeException::new);

        return new WordDetailResponse(
                info.getId(),
                info.getTitle(),
                info.getContent()
        );
    }
}
