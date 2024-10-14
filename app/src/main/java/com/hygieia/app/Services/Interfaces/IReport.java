package com.hygieia.app.Services.Interfaces;

import com.hygieia.app.DTO.ReportDto;
import com.hygieia.app.DTO.ReportResponseDto;

public interface IReport {

ReportResponseDto CreateReport(ReportDto reportDto);
    
}
