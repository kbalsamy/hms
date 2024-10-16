package com.hygieia.app.Services;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import com.hygieia.app.DTO.UserPaymentDto;
import com.hygieia.app.Models.Appointment;
import com.hygieia.app.Models.Order;
import com.hygieia.app.Repositories.OrderRepository;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.Observers.Bill;


import java.util.List;
import java.util.ArrayList;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${PAYMENT_GATEWAY_SECRET_KEY}")
    private String secret_key;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private OrderRepository orderRepository;

     public List<Bill> observers = new ArrayList<>();


    public void attach(Bill observer) {
        observers.add(observer);
    }

    public void notifyAllObservers(UserPaymentDto userPaymentDto) {
        for (Bill observer : observers) {
            observer.update(userPaymentDto);
        }
    }

    public void detach(Bill observer) {
        observers.remove(observer);
    }

    public Appointment confirmPayment ( UserPaymentDto userPayDto){

        try{
            Appointment appointment = appointmentService.findAppointmentById(userPayDto.getAppointmentId()).get();
            float amount=appointment.getAmount();
            

        WebClient webClient = WebClient.create();
            
        CustomToken tokenGenerator = new CustomToken(secret_key);
        System.out.println(tokenGenerator.getToken());

        // Define the URL of the API endpoint for external payment gateway
        String apiUrl = String.format("http://localhost:9091/pay?transferorId=%s&payeeId=2345678910294229&amount=%.2f&payMethod=%s&token=%s",userPayDto.getCard(), amount, userPayDto.getPaymentType(), tokenGenerator.getToken() );
        // String apiUrl = "https://jsonplaceholder.typicode.com/posts/1";
            System.out.println(apiUrl);
        // Make a GET request and retrieve the response
        ClientResponse response = webClient.get()
                .uri(apiUrl)
                .exchange()                
                .block(); 

        if (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
            new Exception("Payment failed");
            return null;
        }
        String responseBody = response.bodyToMono(String.class).block();
        System.out.println(responseBody);
        JSONObject jsonObject = new JSONObject(responseBody);
        System.out.println(jsonObject.getInt("paymentReferenceId"));
        // create orders
        Order order = new Order();
        order.setPatient(patientRepository.findById(userPayDto.getPatientId()).get());
        order.setAmount(amount);
        order.setPaymentType(userPayDto.getPaymentType());
        order.setAppointment(appointment);
        order.setPaymentRef(jsonObject.getInt("paymentReferenceId"));
        orderRepository.save(order);                
        appointment.setStatus("booked");
        appointment.setOrderId(order);
        
        appointmentService.updateAppoinment(appointment);
        // notify all observers
        this.notifyAllObservers(userPayDto);
        return appointment;
            
        
       
    }catch(Exception e){

        System.out.println(e.getMessage());
       return null;
    }

    }
    
}
