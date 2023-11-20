package com.hygieia.app.DTO;

import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {

    private int doctorId;
    private int patientId;
    private LocalDateTime timing;
    private String appointmentType; 
}
    

