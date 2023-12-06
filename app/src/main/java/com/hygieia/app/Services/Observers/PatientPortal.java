package com.hygieia.app.Services.Observers;

import org.springframework.stereotype.Service;
import com.hygieia.app.Services.Interfaces.INotification;
import com.hygieia.app.Services.Interfaces.IPortal;
import com.hygieia.app.Services.Interfaces.IReportUpdater;
import com.hygieia.app.Services.Notification.EmailNotification;
import com.hygieia.app.Services.Notification.PushNotification;
import com.hygieia.app.Services.Notification.SMSNotification;

@Service
public class PatientPortal implements IReportUpdater,IPortal{


    @Override
    public void update() {
        this.updatePatientportal();
        this.sendNotification();
    }

    public void updatePatientportal(){
        System.out.println("Patient portal updated.");

    }

     public void sendNotification() {

        INotification emailNotification=new EmailNotification();
        INotification decoratedNotification=new SMSNotification(emailNotification);
        INotification fullyDecoratedNotification=new PushNotification(decoratedNotification);

        fullyDecoratedNotification.sendNotification("Patient portal is updated");

        System.out.println("Bill sent");
    }
    
    
}
