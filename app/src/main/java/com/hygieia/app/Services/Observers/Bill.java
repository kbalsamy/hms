package com.hygieia.app.Services.Observers;

import com.hygieia.app.Services.Interfaces.IBill;

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
        System.out.println("Bill sent");
    }
    
}
