package com.racinger.librarySystem.Library.Factory;

import com.racinger.librarySystem.Library.DTO.ReportDto;
import org.springframework.stereotype.Component;

@Component
public class ReportFactory {

    public ReportDto createEmptyReportDto() {
        return new ReportDto();
    }

    public ReportDto createReportDto(String reportType) {
        ReportDto dto = new ReportDto();
        dto.setReportType(reportType);
        return dto;
    }
}