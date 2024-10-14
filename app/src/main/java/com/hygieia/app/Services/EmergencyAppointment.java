package com.hygieia.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import com.hygieia.app.Models.Appointment;
import com.hygieia.app.Repositories.AppoinmentRepository;
import com.hygieia.app.Repositories.EmployeeRepository;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.Interfaces.IAppointment;

public class EmergencyAppointment implements IAppointment {
 
    
        @Autowired
    private AppoinmentRepository appoinmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PatientRepository patientRepository;
 
    public EmergencyAppointment(EmployeeRepository employeeRepository, PatientRepository patientRepository,
            AppoinmentRepository appoinmentRepository) {

                this.employeeRepository=employeeRepository;
                this.patientRepository=patientRepository;
                this.appoinmentRepository=appoinmentRepository;
    }

    public float Pay(){

        return 25;

    }

    public int MakeAppointment(int doctorId,int patientId,LocalDateTime timing){
        //,AppoinmentRepository a,PatientRepository p,EmployeeRepository e

        // Calculate Payment.

         float amount=this.Pay();

        Appointment appointment=new Appointment();

        appointment.setEmployee(employeeRepository.findById(doctorId).get());
        appointment.setPatient(patientRepository.findById(patientId).get());
        appointment.setStartTime(timing);
        appointment.setAmount(amount);
        
        Appointment newAppointment=appoinmentRepository.save(appointment);

        int id=newAppointment.getId();
        return id;

    }
}
