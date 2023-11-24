package com.hygieia.app.Controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.AppointmentDto;
import com.hygieia.app.DTO.AppointmentRequestDto;
import com.hygieia.app.DTO.AppointmentResponseDto;
import com.hygieia.app.DTO.UserPaymentDto;
import com.hygieia.app.Models.Appointment;
import com.hygieia.app.Repositories.EmployeeRepository;
import com.hygieia.app.Repositories.OrderRepository;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.AppointmentService;
import com.hygieia.app.Services.PaymentService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PaymentService paymentService;


    // create a new appointment
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        try{
            Appointment appointment = appointmentService.createAppointment(appointmentDto);
            // logger.logInfo("Appointment created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Appointment created successfully", appointment));
        }catch(Exception e){
            // logger.logError("Appointment creation failed", e);
            return ResponseEntity.ok(new ApiResponse(false, "Appointment creation failed", null));
        }
    }

     //get all appoinments
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAppoinments() {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Appoinments fetched successfully", appointmentService.getAllAppointments()));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Appoinments fetch failed", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAppoinmentById(@PathVariable int id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Appoinment retrieved successfully", appointmentService.findAppointmentById(id)));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error retrieving Appoinment", null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateAppoinment(@RequestBody AppointmentDto appointmentDto, @PathVariable int id) {
        try{
            Appointment existingAppointment = appointmentService.findAppointmentById(id).orElseThrow(()->new ResourceNotFoundException(
                    "Appoinment not found"));          
            existingAppointment.setEmployee(employeeRepository.findById(appointmentDto.getDocId()).get());
            existingAppointment.setPatient(patientRepository.findById(appointmentDto.getPatientId()).get());
            existingAppointment.setStartTime(appointmentDto.getStartTime());
            existingAppointment.setStatus(appointmentDto.getStatus());
            existingAppointment.setOrderId(orderRepository.findById(appointmentDto.getOrderId()).get());
            
            Appointment updatedAppoinment = appointmentService.updateAppoinment(existingAppointment);

            // logger.logInfo("Org Unit updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Appoinment updated successfully", updatedAppoinment));
        }catch(ResourceNotFoundException e){
            // logger.logError("Org Unit update failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @PostMapping("/request")
    public ResponseEntity<ApiResponse> requestAppoinment(@RequestBody AppointmentRequestDto reqDto){

        
        try{
                //appointmentService.initAppoinment(1,2,reqDto.getTiming());
            AppointmentResponseDto response=appointmentService.checkAppointmentSlot(reqDto);
            
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Appoinment initiated request for payment", response));

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getMessage(), null));

        }
    }

    @PostMapping("/confirm/payment")
    public ResponseEntity<ApiResponse> ConfirmPayment(@RequestBody UserPaymentDto userPayDto ){

        try{            
            Appointment appointment = paymentService.confirmPayment(userPayDto);            
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Appoinment confirmed", appointment));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getMessage(), null));

        }

    }
    
}
