package com.hackton.backend.domain.info.service;

import com.hackton.backend.domain.info.domain.InfoEntity;
import com.hackton.backend.domain.info.domain.InfoRepository;
import com.hackton.backend.domain.info.presentation.dto.InfoFilter;
import com.hackton.backend.domain.info.presentation.dto.response.InfoStatusElement;
import com.hackton.backend.domain.info.presentation.dto.response.InfoStatusListResponse;
import com.hackton.backend.domain.info.presentation.dto.response.WordDetailResponse;
import com.hackton.backend.domain.info.presentation.dto.response.WordElement;
import com.hackton.backend.domain.info.presentation.dto.response.WordListResponse;
import com.hackton.backend.domain.status.domain.StatusEntity;
import com.hackton.backend.domain.status.domain.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InfoService {

    private final InfoRepository infoRepository;
    private final StatusRepository statusRepository;

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

    public InfoStatusListResponse getInfoListByInfoFilter(
            String title,
            LocalDate date
    ) {
        InfoFilter infoFilter = new InfoFilter(title, date);
        List<StatusEntity> statusEntities = statusRepository.findAll();

        List<InfoStatusElement> infoElements = infoRepository.findAllByInfoFilter(infoFilter)
                .stream()
                .map(info -> {
                    List<String> statusList = statusEntities.stream()
                            .filter(st -> st.getInfo().getId().equals(info.getId()))
                            .map(StatusEntity::getName)
                            .toList();

                    return InfoStatusElement.builder()
                            .id(info.getId())
                            .title(info.getTitle())
                            .content(info.getContent())
                            .statusName(statusList)
                            .build();
                })
                .toList();

        return new InfoStatusListResponse(infoElements);
    }

    public void deleteInfo(Long infoId) {
        InfoEntity info = infoRepository.findById(infoId)
                .orElseThrow(RuntimeException::new);

        infoRepository.delete(info);
    }
}
