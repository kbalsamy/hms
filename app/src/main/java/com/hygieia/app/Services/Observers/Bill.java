package com.hygieia.app.Services.Observers;

import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.UserPaymentDto;
import com.hygieia.app.Services.PatientService;
import com.hygieia.app.Services.Interfaces.IBill;
import com.hygieia.app.Services.Interfaces.IHealthPlans;
import com.hygieia.app.Services.Interfaces.INotification;
import com.hygieia.app.Services.Notification.EmailNotification;
import com.hygieia.app.Services.Notification.PushNotification;
import com.hygieia.app.Services.Notification.SMSNotification;

@Service
public class Bill implements IBill {

    private PatientService patientService;

    public Bill(PatientService patientService)
    {
        this.patientService=patientService;

    }

    @Override
    public void update(UserPaymentDto userPaymentDto) {
        this.generateBill(userPaymentDto);
        this.sendNotification();
    }


    public void generateBill(UserPaymentDto userPaymentDto) {


        IHealthPlans healthPlan=patientService.getHealthPlans(userPaymentDto.getHealthPlan());

        System.out.println(healthPlan.getHealthPlanCode());
        System.out.println(healthPlan.getCharge());
        
        System.out.println("Bill generated");
    }

    public void sendNotification() {

        INotification emailNotification=new EmailNotification();
        INotification decoratedNotification=new SMSNotification(emailNotification);
        INotification fullyDecoratedNotification=new PushNotification(decoratedNotification);

        fullyDecoratedNotification.sendNotification("Your Appointmnet is ready");

        System.out.println("Bill sent");
    }
    
}
