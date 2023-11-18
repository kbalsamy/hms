package com.hygieia.app.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.Models.Patient;
import com.hygieia.app.Repositories.PatientRepository;

@Service
public class PatientService {

     @Autowired
    private PatientRepository patRepo;

    public Patient SavePatient(Patient patient){

        if(patRepo.existsByUserName(patient.getUserName())){
            return null;
        }
         
        
        Patient newPatient = patRepo.save(patient) ;
        
        return newPatient;

        
    }

     public Optional<Patient> findPatientById(int id){

        return patRepo.findById(id);

       
    }

    
}
