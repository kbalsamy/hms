package com.hygieia.app.DTO;

import com.hygieia.app.Models.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    float amount;

    PaymentType paymentType;

    int patientId;

    int appointmentId;
    
}
