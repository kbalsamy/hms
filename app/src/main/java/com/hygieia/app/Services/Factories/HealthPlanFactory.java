package com.hygieia.app.Services.Factories;

import org.springframework.stereotype.Service;

import com.hygieia.app.Models.HealthPlans;
import com.hygieia.app.Services.Interfaces.IHealthPlanFactory;
import com.hygieia.app.Services.Interfaces.IHealthPlans;
import com.hygieia.app.Services.plans.BasicPlan;
import com.hygieia.app.Services.plans.GoldPlan;
import com.hygieia.app.Services.plans.PlatinumPlan;

@Service
public class HealthPlanFactory implements IHealthPlanFactory {

    
    public IHealthPlans Create( String planCode){

        if (planCode.equals(HealthPlans.BASIC.name())) {

            return new BasicPlan();

        } else if (planCode.equals(HealthPlans.BASIC.name())) {
            return new GoldPlan();

        } else {
            return new PlatinumPlan();
        }

       
    }
    
}
