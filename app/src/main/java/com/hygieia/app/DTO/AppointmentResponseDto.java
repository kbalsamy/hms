package com.hygieia.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {

    private int doctorId;
    private int patientId;
    private float amount;
    private LocalDateTime date;
    private int appointmentId;

}
