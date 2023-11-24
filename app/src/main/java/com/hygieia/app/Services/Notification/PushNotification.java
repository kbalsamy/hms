package com.hygieia.app.Services.Notification;

import com.hygieia.app.Services.Interfaces.INotification;
import com.hygieia.app.Services.Interfaces.NotificationDecorator;

public class PushNotification implements NotificationDecorator{

    private final INotification notification;

    public PushNotification(INotification notification){
        this.notification=notification;

    }
    public void sendNotification(){
       
        notification.sendNotification();
        System.out.println("Push Notification : Your appointment is ready");
    
    
    
    
        }
    
}
