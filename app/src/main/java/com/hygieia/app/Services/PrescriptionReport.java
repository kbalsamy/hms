package com.hygieia.app.Services;

import com.hygieia.app.DTO.ReportDto;
import com.hygieia.app.DTO.ReportResponseDto;
import com.hygieia.app.Models.PrescriptionReportData;
import com.hygieia.app.Services.Interfaces.IReport;

public class PrescriptionReport implements IReport{
    
    public ReportResponseDto CreateReport(ReportDto reportDto){

        PrescriptionReportData patPrescriptionReport=new PrescriptionReportData();

        patPrescriptionReport.setDoctorId(reportDto.getDoctorId());
        patPrescriptionReport.setPatientId(reportDto.getPatientId());
        patPrescriptionReport.setMedicines(reportDto.getMedicines());

        ReportResponseDto rep=new ReportResponseDto();

        rep.setDoctorId(reportDto.getDoctorId());
        rep.setPatientId(reportDto.getPatientId());
        rep.setMedicines(reportDto.getMedicines());


        return rep;
    

    }
}
