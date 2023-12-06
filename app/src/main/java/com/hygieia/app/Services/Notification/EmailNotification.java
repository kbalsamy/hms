package com.hygieia.app.Services.Notification;

import com.hygieia.app.Services.Interfaces.INotification;

public class EmailNotification implements INotification {

    public void sendNotification(String msg) {

        System.out.println("Email Notification : " + msg);

    }

}
