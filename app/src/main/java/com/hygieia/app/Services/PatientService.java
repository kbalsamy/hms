package com.hygieia.app.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.Models.HealthPlans;
import com.hygieia.app.Models.Patient;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.Interfaces.IHealthPlans;
import com.hygieia.app.Services.States.BasicPlan;
import com.hygieia.app.Services.States.GoldPlan;
import com.hygieia.app.Services.States.PlatinumPlan;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patRepo;

    public Patient SavePatient(Patient patient) {

        if (patRepo.existsByUserName(patient.getUserName())) {
            return null;
        }

        Patient newPatient = patRepo.save(patient);

        return newPatient;

    }

    public Optional<Patient> findPatientById(int id) {

        return patRepo.findById(id);

    }

    public IHealthPlans getHealthPlans(String planCode) {

        if (planCode.equals(HealthPlans.BASIC.name())) {

            return new BasicPlan();

        } else if (planCode.equals(HealthPlans.BASIC.name())) {
            return new GoldPlan();

        } else {
            return new PlatinumPlan();
        }

    }

}
