package com.hygieia.app.DTO;

import com.hygieia.app.Models.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPaymentDto {

    private int appointmentId;

    private int patientId;

    private PaymentType paymentType;

    private String card;

    private int cvv;

    private String doe;

    private String cardName;

    private String phoneNo;

    
}
