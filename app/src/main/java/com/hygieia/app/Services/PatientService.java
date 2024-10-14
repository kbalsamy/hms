package com.hygieia.app.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.Models.HealthPlans;
import com.hygieia.app.Models.Patient;
import com.hygieia.app.Repositories.PatientRepository;
import com.hygieia.app.Services.Factories.HealthPlanFactory;
import com.hygieia.app.Services.Interfaces.IHealthPlans;
import com.hygieia.app.Services.plans.BasicPlan;
import com.hygieia.app.Services.plans.GoldPlan;
import com.hygieia.app.Services.plans.PlatinumPlan;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patRepo;

    @Autowired
    private HealthPlanFactory healthPlanFactory;

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

       return healthPlanFactory.Create(planCode);

    }

}
