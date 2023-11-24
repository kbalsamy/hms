package com.hygieia.app.Services.Notification;

import com.hygieia.app.Services.Interfaces.INotification;
import com.hygieia.app.Services.Interfaces.NotificationDecorator;

public class SMSNotification implements NotificationDecorator  {

    private final INotification notification;

    public SMSNotification(INotification notification){
        this.notification=notification;

    }
    
    public void sendNotification(){
        notification.sendNotification();
        System.out.println("SMS Notification : Your appointment is ready");
    
        }
}
