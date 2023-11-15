package com.hygieia.app.Models;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "AppointmentDetails")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @Column(nullable = false)
    private int docId;

    @Column(nullable = false)
    private Date dateOfAppointment;

    @CreationTimestamp
    private LocalDateTime dateCreated; 

}
