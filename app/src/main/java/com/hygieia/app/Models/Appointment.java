package com.hygieia.app.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)  
    @JsonIgnore
    private Patient patient;

    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)  
    @JsonIgnore
    private Employee employee;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @CreationTimestamp
    private LocalDateTime dateCreated; 

    private Status status;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id",referencedColumnName="id")
    
    private Order orderId;

}
