package com.hygieia.app.Services.States;

import com.hygieia.app.Models.HealthPlans;
import com.hygieia.app.Services.Interfaces.IHealthPlans;

public class GoldPlan implements IHealthPlans{

     public String getHealthPlanCode(){
        return HealthPlans.GOLD.name();
    }

    public float getCharge(){
        return  40;
    }
    
}
