package com.hygieia.app.Services;

import com.hygieia.app.Services.Interfaces.IAppointment;

public abstract class AppointmentFactory {

    public IAppointment create(){
        IAppointment app=createAppointment();
        app.CreateAppointment();
        return app;
    }

    protected abstract IAppointment createAppointment();
    
}
