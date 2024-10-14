package com.hygieia.app.Services.Factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.Repositories.AppoinmentRepository;
import com.hygieia.app.Repositories.EmployeeRepository;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.EmergencyAppointment;
import com.hygieia.app.Services.RegularAppointment;
import com.hygieia.app.Services.Interfaces.IAppointment;


@Service
public class AppointmentFactory {

    @Autowired
    EmployeeRepository employeeRepository;

     @Autowired
    PatientRepository patientRepository;

     @Autowired
    AppoinmentRepository appointmentFactory;

   
    public IAppointment Create( String type){

        if(type.equals("Regular")){
            return new RegularAppointment(employeeRepository,patientRepository,appointmentFactory);
        }
        else{
            return new EmergencyAppointment(employeeRepository,patientRepository,appointmentFactory);

        }

       
    }
    
}
