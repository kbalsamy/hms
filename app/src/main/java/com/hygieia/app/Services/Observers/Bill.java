package com.hygieia.app.Services.Observers;

import com.hygieia.app.Services.Interfaces.IBill;
import com.hygieia.app.Services.Interfaces.INotification;
import com.hygieia.app.Services.Notification.EmailNotification;
import com.hygieia.app.Services.Notification.PushNotification;
import com.hygieia.app.Services.Notification.SMSNotification;


public class Bill implements IBill {

    @Override
    public void update() {
        this.generateBill();
        this.sendNotification();
    }

    public void generateBill() {
        System.out.println("Bill generated");
    }

    public void sendNotification() {

        INotification emailNotification=new EmailNotification();
        INotification decoratedNotification=new SMSNotification(emailNotification);
        INotification fullyDecoratedNotification=new PushNotification(decoratedNotification);

        fullyDecoratedNotification.sendNotification();

        System.out.println("Bill sent");
    }
    
}
