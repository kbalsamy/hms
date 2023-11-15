package com.hygieia.app.Services;

import com.hygieia.app.Services.Interfaces.IAppointment;

public class EmergencyAppointmentFactory {

    protected IAppointment createAppointment(){
        return new EmergencyAppointment();
    }
    
}
