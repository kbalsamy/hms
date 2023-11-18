package com.hygieia.app.Services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.EmployeeTypeDto;
import com.hygieia.app.Models.EmployeeType;
import com.hygieia.app.Repositories.EmployeeTypeRepository;

@Service
public class EmployeeTypeService {

    @Autowired
    private EmployeeTypeRepository empTypeRepo;


      public EmployeeType saveRole(EmployeeTypeDto empTypeDto) {

        if(empTypeRepo.existsByEmpTypeName(empTypeDto.getEmpTypeName())){
            return null;
        }

        EmployeeType empType = new EmployeeType();
        empType.setEmpTypeName(empTypeDto.getEmpTypeName());
 

        EmployeeType newEmpType = empTypeRepo.save(empType);
        return newEmpType;
    }


    public List<EmployeeType> getAllEmployeeTypes(){


        List<EmployeeType> empTypes=empTypeRepo.findAll();

        return empTypes;

    }

    public EmployeeType findEmpTypeByEmpTypeName(String name){

        return empTypeRepo.findByEmpTypeName(name);

       
    }

    public Optional<EmployeeType> findEmpTypeNameById(int id){
        return empTypeRepo.findById(id);
    }

}
