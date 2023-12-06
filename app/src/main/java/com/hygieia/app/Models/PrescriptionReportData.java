package com.hygieia.app.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionReportData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int doctorId;

    private int patientId;

   private List<String> medicines;
    
}
