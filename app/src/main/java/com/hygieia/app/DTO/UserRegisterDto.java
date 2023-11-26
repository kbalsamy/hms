package com.hygieia.app.DTO;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    private String userName;

    private String userPassword;

    private String firstName;

    private String lasttName;

    private Date dob;

    private String gender;

    private int phoneNo;

    private String address;

    private String healthPlan;
    
}
