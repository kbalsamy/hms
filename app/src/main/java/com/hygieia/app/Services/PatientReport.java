package com.hygieia.app.Services;

import com.hygieia.app.DTO.ReportDto;
import com.hygieia.app.DTO.ReportResponseDto;
import com.hygieia.app.Models.PatientReportData;
import com.hygieia.app.Services.Interfaces.IReport;

public class PatientReport implements IReport {

    public ReportResponseDto CreateReport(ReportDto reportDto){

        PatientReportData patReport=new PatientReportData();

        patReport.setDoctorId(reportDto.getDoctorId());
        patReport.setPatientId(reportDto.getPatientId());
        patReport.setRemarks(reportDto.getRemarks());

        ReportResponseDto rep=new ReportResponseDto();

        rep.setDoctorId(reportDto.getDoctorId());
        rep.setPatientId(reportDto.getPatientId());
        rep.setRemarks(reportDto.getRemarks());

        return rep;




    }
    
}
