package com.hygieia.app.Models;

import java.time.LocalDateTime;
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

    @Column(nullable = false)
    private int patId;

    @Column(nullable = false)
    private int docId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime dateCreated; 

    private Status status;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id",referencedColumnName="id")
    private Order orderId;

}
