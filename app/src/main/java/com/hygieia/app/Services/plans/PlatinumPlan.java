package com.hygieia.app.Services.plans;

import com.hygieia.app.Models.HealthPlans;
import com.hygieia.app.Services.Interfaces.IHealthPlans;

public class PlatinumPlan implements IHealthPlans {

 public String getHealthPlanCode(){
        return HealthPlans.PLATINUM.name();
    }

    public float getCharge(){
        return  60;
    }
    
}
