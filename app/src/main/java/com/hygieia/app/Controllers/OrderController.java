package com.hygieia.app.Controllers;



import org.apache.velocity.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.OrderDto;

import com.hygieia.app.Models.Order;

import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.OrderService;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.AppointmentService;



@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDto orderDto) {

        try{
            Order order = orderService.saveOrder(orderDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "order created successfully", order));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));

        }

    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrders() {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Orders fetched successfully", orderService.getAllOrders()));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Orders fetch failed", null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateOrder(@RequestBody OrderDto orderDto, @PathVariable int id) {
        try{           
            
            Order existingOrder=orderService.findOrderById(id).orElseThrow(()->new ResourceNotFoundException("Order not found"));
            existingOrder.setAmount(orderDto.getAmount());
            existingOrder.setPaymentType(orderDto.getPaymentType());
            existingOrder.setPatient(patientRepository.findById(orderDto.getPatientId()).get());
            existingOrder.setAppointment(appointmentService.findAppointmentById(orderDto.getAppointmentId()).get());
            Order updatedOrder =orderService.updateOrder(existingOrder);
            // logger.logInfo("Org Unit updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "order updated successfully", updatedOrder));
        }catch(ResourceNotFoundException e){
            // logger.logError("Org Unit update failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable int id) {
        try{

            Order order = orderService.findOrderById(id).orElseThrow(()->new ResourceNotFoundException(
                    "Order not found"));
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Order retrieved successfully", order));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error retrieving Order", null));
        }
    }


    
}
