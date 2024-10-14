package com.hygieia.app.Services.Interfaces;

import java.time.LocalDateTime;

public interface IAppointment {

    float Pay();

    int MakeAppointment(int doctorId,int patientId,LocalDateTime timing); //,AppoinmentRepository a,PatientRepository p,EmployeeRepository e
    
}
