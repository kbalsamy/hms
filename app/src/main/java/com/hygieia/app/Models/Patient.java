package com.hygieia.app.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String firstName;

    @Column(nullable = false, unique = true)
    private String lasttName;

    @Column(nullable = false, unique = false)
    private Date dob;

    @Column(nullable = false, unique = false)
    private String gender;

    @Column(nullable = false, unique = true)
    private int phoneNo;

    @Column(nullable = false, unique = false)
    private String address;

    @OneToMany(mappedBy = "patient")
    private List<Order> orders;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    

}
