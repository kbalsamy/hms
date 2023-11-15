package com.hygieia.app.Services;

import com.hygieia.app.Services.Interfaces.IAppointment;

public class RegularAppointmentFactory {

    protected IAppointment createRegularAppointment(){
        return new RegularAppointment();
    }
    
}
