package com.hygieia.app.DTO;


import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDto {

    Date date;

    LocalDateTime fromTime;

    LocalDateTime toTime;

    int employee;
    
}
