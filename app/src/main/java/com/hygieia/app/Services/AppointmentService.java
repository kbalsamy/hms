package com.hygieia.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hygieia.app.DTO.AppointmentDto;
import com.hygieia.app.DTO.AppointmentRequestDto;
import com.hygieia.app.DTO.AppointmentResponseDto;
import com.hygieia.app.Models.Appointment;
import com.hygieia.app.Models.AppointmentType;
import com.hygieia.app.Repositories.AppoinmentRepository;
import com.hygieia.app.Repositories.EmployeeRepository;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.Factories.AppointmentFactory;
import com.hygieia.app.Services.Interfaces.IAppointment;

@Service
public class AppointmentService {


    @Autowired
    private AppoinmentRepository appointmentRepository;


    private IAppointment newAppointment;

    @Autowired
    private AppointmentFactory appointmentFactory;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Appointment createAppointment(AppointmentDto appointmentDto) {

        

        

        // business func - for Regular
        // 1. check doctor slot availabilty on the requuested slot
        // 2. calculate the amount 
        // 3. 




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

    public AppointmentResponseDto checkAppointmentSlot(AppointmentRequestDto reqDto){


        if(reqDto.getAppointmentType().equals(AppointmentType.REGULAR.name())){
            newAppointment=appointmentFactory.Create("Regular");
        }
        else{
            newAppointment=appointmentFactory.Create("Emergency");

        }

        int id=newAppointment.MakeAppointment(reqDto.getDoctorId(),reqDto.getPatientId(),reqDto.getTiming());
        //,appointmentRepository,patientRepository,employeeRepository
        float amount=newAppointment.Pay();

        AppointmentResponseDto responseDto=new AppointmentResponseDto();

        responseDto.setDoctorId(reqDto.getDoctorId());
        responseDto.setPatientId(reqDto.getPatientId());
        responseDto.setAppointmentId(id);
        responseDto.setAmount(amount);

        return responseDto;

    }

    // public int initAppoinment(int doctorId, int patientId, LocalDateTime timing){

    //     // if(reqDto.getAppointmentType().equals(AppointmentType.REGULAR.name())){
    //     //     newAppointment=appointmentFactory.Create("Regular");
    //     // }
    //     // else{
    //     //     newAppointment=appointmentFactory.Create("Emergency");

    //     // }

    //     Appointment appointment=new Appointment();

    //     appointment.setEmployee(employeeRepository.findById(doctorId).get());
    //     appointment.setPatient(patientRepository.findById(patientId).get());
    //     appointment.setStartTime(timing);
    //     //appointment.setAmount(amount);
        
    //    Appointment newAppointment=appointmentRepository.save(appointment);

    //     int id=newAppointment.getId();

    //     return id;

    // }






    
}
