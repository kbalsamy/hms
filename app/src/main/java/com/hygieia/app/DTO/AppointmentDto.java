package com.hygieia.app.DTO;

import java.time.LocalDateTime;

import com.hygieia.app.Models.AppointmentType;
import com.hygieia.app.Models.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    int patientId;
    int docId;
    LocalDateTime startTime;
    String status;
    int orderId;
    AppointmentType appointmentType;
    
}
