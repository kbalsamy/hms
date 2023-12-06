package com.hygieia.app.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {

    private int doctorId;

    private int patientId;

    private String reportType;

    private String remarks;

    private List<String> medicines;

    private LocalDateTime date;
    
}
