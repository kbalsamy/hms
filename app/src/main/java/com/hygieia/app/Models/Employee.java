package com.hygieia.app.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

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

    @Column(nullable = false, unique = false)

    private String designation;

    @Column(nullable = false, unique = true)

    private int phoneNo;

    @Column(nullable = false, unique = false)

    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private EmployeeType emptype;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Availability> availabilities;
}
