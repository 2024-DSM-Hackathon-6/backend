package com.hackton.backend.infra.excel;

import com.hackton.backend.domain.info.domain.InfoCategoryEntity;
import com.hackton.backend.domain.info.domain.InfoEntity;
import com.hackton.backend.domain.info.domain.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ExcelService {

    private final InfoRepository infoRepository;

    public void excelParsing() {
        try (FileInputStream file = new FileInputStream("dictionary.xlsx");) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            List<InfoEntity> infoEntities = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                infoEntities.add(
                        InfoEntity.builder()
                                .title(row.getCell(2).toString())
                                .content(row.getCell(3).toString())
                                .categoryName(row.getCell(1).toString())
                                .infoCategory(InfoCategoryEntity.getDefaultCategory())
                                .build()
                );
            }
            infoRepository.saveAll(infoEntities);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
