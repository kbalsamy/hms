package com.hygieia.app.Services;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.hygieia.app.DTO.UserPaymentDto;
import com.hygieia.app.Models.Appointment;
import com.hygieia.app.Models.Order;
import com.hygieia.app.Repositories.AppoinmentRepository;
import com.hygieia.app.Repositories.OrderRepository;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.Observers.Bill;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

     public List<Bill> observers = new ArrayList<>();


    public void attach(Bill observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Bill observer : observers) {
            observer.update();
        }
    }

    public void detach(Bill observer) {
        observers.remove(observer);
    }

    public Appointment confirmPayment ( UserPaymentDto userPayDto){

        try{

        WebClient webClient = WebClient.create();

        // Define the URL of the API endpoint for external payment gateway
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/1";

        // Make a GET request and retrieve the response
        ClientResponse response = webClient.get()
                .uri(apiUrl)
                .exchange()                
                .block(); 

        if (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
            new Exception("Payment failed");
            return null;
        }
        Appointment appointment = new Appointment();
        // Appointment appointment = appointmentService.findAppointmentById(userPayDto.getAppointmentId()).get();
        // String responseBody = response.bodyToMono(String.class).block();
        // create order
        // Order order = new Order();
        // order.setPatient(patientRepository.findById(1).get());
        // order.setAmount(1000);
        // order.setPaymentType(userPayDto.getPaymentType());
        // order.setAppointment(appointment);
        // orderRepository.save(order);                
        appointment.setStatus("booked");
        // appointmentService.updateAppoinment(appointment);
        // notify all observers
        this.notifyAllObservers();
        return appointment;
            
        
       
    }catch(Exception e){
        new Exception("Server error");
        return null;
    }

    }
    
}
