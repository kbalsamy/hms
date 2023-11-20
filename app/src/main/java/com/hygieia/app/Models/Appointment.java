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


    // @Setter(AccessLevel.NONE)
    // private String defStatus="PENDING";

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
    
    @Column(columnDefinition = "boolean default true")
    private String status;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id",referencedColumnName="id")
    
    private Order orderId;

    private float amount;


    // public void setStatus(String status){
    //    if (status == null){
    //     this.status = this.defStatus;
    //    }
    // }


}
