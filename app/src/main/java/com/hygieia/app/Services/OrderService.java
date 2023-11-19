package com.hygieia.app.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.OrderDto;
import com.hygieia.app.Models.Order;
import com.hygieia.app.Repositories.AppoinmentRepository;
import com.hygieia.app.Repositories.OrderRepository;
import com.hygieia.app.Repositories.PatientRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired 
    PatientRepository patientRepository;

    @Autowired
    AppoinmentRepository appointmentRepository;

    public Order saveOrder(OrderDto orderDto) {
        
        Order order = new Order();

        order.setAmount(orderDto.getAmount());
        order.setPaymentType(orderDto.getPaymentType());
        order.setPatient(patientRepository.findById(orderDto.getPatientId()).get());
        order.setAppointment(appointmentRepository.findById(orderDto.getAppointmentId()).get());

        if(order.getPatient() == null){
            new Exception("Patient not found");
        }
        Order newOrder = orderRepository.save(order);
        return newOrder;
    }

    public Order updateOrder(Order orderDto) {

       Order updated =  orderRepository.save(orderDto);
       return updated;

    }

    public List<Order> getAllOrders(){

        List<Order> orders=orderRepository.findAll();

        return orders;

    }

    public Optional<Order> findOrderById(int id){

        return orderRepository.findById(id);

    }

    
    
}
