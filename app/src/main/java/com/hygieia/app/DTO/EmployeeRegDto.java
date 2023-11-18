package com.hygieia.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

import com.hygieia.app.Models.Department;
import com.hygieia.app.Models.EmployeeType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegDto {

    private String userName;

    private String userPassword;

    private String firstName;

    private String lasttName;

    private Date dob;

    private String gender;

    private String designation;

    private int phoneNo;

    private String address;

    private int empType;

    private long department;

    private String role;
    
}
