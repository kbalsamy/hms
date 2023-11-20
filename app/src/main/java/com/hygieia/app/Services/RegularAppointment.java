package com.hygieia.app.Services;

import java.time.LocalDateTime;

import com.hygieia.app.Models.Appointment;
import com.hygieia.app.Repositories.AppoinmentRepository;
import com.hygieia.app.Repositories.EmployeeRepository;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.Interfaces.IAppointment;


public class RegularAppointment implements IAppointment {

    EmployeeRepository employeeRepository;


    PatientRepository patientRepository;


    AppoinmentRepository appoinmentRepository;

    public RegularAppointment(EmployeeRepository employeeRepository, PatientRepository patientRepository,
            AppoinmentRepository appoinmentRepository) {
                this.employeeRepository=employeeRepository;
                this.patientRepository=patientRepository;
                this.appoinmentRepository=appoinmentRepository;
    }


    public float Pay() {

        return 25;

    }


    public int MakeAppointment(int doctorId, int patientId, LocalDateTime timing) {
        // ,AppoinmentRepository a,PatientRepository p,EmployeeRepository e

        // Calculate Payment.

        float amount = this.Pay();

        //AppointmentService service = new AppointmentService();

        // int id = service.initAppoinment(doctorId, patientId, timing);

        Appointment appointment = new Appointment();

        appointment.setEmployee(employeeRepository.findById(doctorId).get());
        appointment.setPatient(patientRepository.findById(patientId).get());
        appointment.setStartTime(timing);
        appointment.setAmount(amount);
        appointment.setStatus("PENDING");

        Appointment newAppointment = appoinmentRepository.save(appointment);

        int id = newAppointment.getId();
        return id;

    }


    
}
