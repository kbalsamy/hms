package com.hygieia.app.Services.Factories;

import org.springframework.stereotype.Service;

import com.hygieia.app.Services.EmergencyAppointment;
import com.hygieia.app.Services.PatientReport;
import com.hygieia.app.Services.PrescriptionReport;
import com.hygieia.app.Services.RegularAppointment;
import com.hygieia.app.Services.Interfaces.IAppointment;
import com.hygieia.app.Services.Interfaces.IReport;

@Service
public class ReportFactory {

     public IReport Create( String type){

        if(type.equals("Patient")){
            return new PatientReport();
        }
        else{
            return new PrescriptionReport();

        }

       
    }
    
}
