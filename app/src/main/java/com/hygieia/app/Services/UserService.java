package com.hygieia.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hygieia.app.DTO.UserRegisterDto;
import com.hygieia.app.Models.Patient;
import com.hygieia.app.Repositories.*;

@Service
public class UserService {

    @Autowired
    private AuthUserRepository AuthUserRepo;


    @Autowired
    private PatientService patService;

  

    public Patient saveUser(UserRegisterDto userregDto) {

        if(AuthUserRepo.existsByUserName(userregDto.getUserName())){
            return null;
        }

        Patient user = new Patient();
        user.setUserName(userregDto.getUserName());
        user.setFirstName(userregDto.getFirstName());
        user.setLasttName(userregDto.getLasttName());
        user.setDob(userregDto.getDob());
        user.setGender(userregDto.getGender());
        user.setPhoneNo(userregDto.getPhoneNo());
        user.setAddress(userregDto.getAddress());
        user.setHealthPlan(userregDto.getHealthPlan());


        Patient newPatient = patService.SavePatient(user);
        return newPatient;
    }

}
