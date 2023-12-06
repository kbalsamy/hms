package com.hygieia.app.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.ReportDto;
import com.hygieia.app.DTO.ReportResponseDto;
import com.hygieia.app.Models.ReportType;
import com.hygieia.app.Services.Factories.ReportFactory;
import com.hygieia.app.Services.Interfaces.IPortal;
import com.hygieia.app.Services.Interfaces.IReport;

@Service
public class ReportService {


    private IReport report;

    @Autowired
    private ReportFactory reportFactory;


      public List<IPortal> observers = new ArrayList<>();


    public void attach(IPortal observer) {
        observers.add(observer);
    }
 
    public void notifyAllObservers() {
        for (IPortal observer : observers) {
            observer.update();
        }
    }

    public void detach(Object observer) {
        observers.remove(observer);
    }


    public ReportResponseDto CreateReport(ReportDto reportDto){

          if(reportDto.getReportType().equals(ReportType.PATIENT.name())){
            report=reportFactory.Create("Patient");
        }
        else{
            report=reportFactory.Create("Prescription");

        }

           
        ReportResponseDto rep=report.CreateReport(reportDto);
        this.notifyAllObservers();

        return rep;
    }
    
}
