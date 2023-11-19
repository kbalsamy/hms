package com.hygieia.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.AppointmentDto;
import com.hygieia.app.Models.Appointment;
import com.hygieia.app.Models.Department;
import com.hygieia.app.Repositories.AppoinmentRepository;
import com.hygieia.app.Repositories.EmployeeRepository;
import com.hygieia.app.Repositories.OrderRepository;
import com.hygieia.app.Repositories.PatientRepository;

@Service
public class AppointmentService {

    // @Autowired
    // private final AppointmentFactory appointmnetfactory;

    // public AppointmentService(AppointmentFactory appointmnetfactory){
    //     this.appointmnetfactory=appointmnetfactory;

    // }

    @Autowired
    private AppoinmentRepository appointmentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Appointment createAppointment(AppointmentDto appointmentDto) {

        Appointment newAppointment = new Appointment();
        newAppointment.setEmployee(employeeRepository.findById(appointmentDto.getDocId()).get());
        newAppointment.setPatient(patientRepository.findById(appointmentDto.getPatientId()).get());
        newAppointment.setStartTime(appointmentDto.getStartTime());
        newAppointment.setStatus(appointmentDto.getStatus());        
        // newAppointment.setOrderId(orderRepository.findById(appointmentDto.getOrderId()).get());
        appointmentRepository.save(newAppointment);
        return newAppointment;

    }

    public Appointment updateAppoinment(Appointment appointmentDto) {

        Appointment updated = appointmentRepository.save(appointmentDto);
        return updated;

    }

    public Optional<Appointment> findAppointmentById(int id) {

        return appointmentRepository.findById(id);

    }

    public List<Appointment> getAllAppointments(){


        List<Appointment> appointments=appointmentRepository.findAll();

        return appointments;

    }







    
}
