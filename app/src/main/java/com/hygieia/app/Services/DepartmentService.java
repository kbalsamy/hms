package com.hygieia.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.DepartmentDto;
import com.hygieia.app.Models.Department;

import com.hygieia.app.Repositories.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    public Department saveDepartment(DepartmentDto departmentDto) {

        if (departmentRepo.existsDepartmentByName(departmentDto.getName())) {
            return null;
        }

        Department newDepartment = new Department();
        newDepartment.setName(departmentDto.getName());
        newDepartment.setDescription(departmentDto.getDescription());
        departmentRepo.save(newDepartment);
        return newDepartment;
    }

    public void updateDepartment(Department departmentDto) {

        departmentRepo.save(departmentDto);

    }

    public Optional<Department> findDepartmentById(Long id){

        return departmentRepo.findById(id);

       
    }

    public List<Department> getAllDepartments(){


        List<Department> departments=departmentRepo.findAll();

        return departments;

    }

    public Optional<Department> findDepartmentByName(String name){

        return departmentRepo.findDepartmentByName(name);

       
    }



}
