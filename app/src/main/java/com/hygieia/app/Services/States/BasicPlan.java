package com.hygieia.app.Services.States;

import com.hygieia.app.Models.HealthPlans;
import com.hygieia.app.Services.Interfaces.IHealthPlans;

public class BasicPlan implements IHealthPlans{

    public String getHealthPlanCode(){
        
        return HealthPlans.BASIC.name();
    }

    public float getCharge(){
        return  20;
    }
    
}
