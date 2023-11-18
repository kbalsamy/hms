package com.hygieia.app.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.EmployeeRegDto;
import com.hygieia.app.Models.Department;
import com.hygieia.app.Models.Employee;
import com.hygieia.app.Models.EmployeeType;
import com.hygieia.app.Repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private EmployeeTypeService empTypeService;

    @Autowired
    private DepartmentService depService;

    public Employee saveUser(EmployeeRegDto empregDto) {

        EmployeeType empType = empTypeService.findEmpTypeNameById(empregDto.getEmpType()).get();

        Department dep = depService.findDepartmentById(empregDto.getDepartment()).get();

        if (empRepo.existsByUserName(empregDto.getUserName())) {
            return null;
        }

        Employee emp = new Employee();
        emp.setUserName(empregDto.getUserName());
        // user.setUserEmail(userregDto.getUserEmail());
        // user.setUserPassword(passwordEncoder.encode(userregDto.getUserPassword()));
        emp.setFirstName(empregDto.getFirstName());
        emp.setLasttName(empregDto.getLasttName());
        emp.setDob(empregDto.getDob());
        emp.setGender(empregDto.getGender());
        emp.setPhoneNo(empregDto.getPhoneNo());
        emp.setAddress(empregDto.getAddress());
        emp.setDepartment(dep);
        emp.setEmptype(empType);
        emp.setDesignation(empregDto.getDesignation());

        Employee newEmployee = empRepo.save(emp);
        return newEmployee;
    }

    public Optional<Employee> findEmployeeById(int id){

        return empRepo.findById(id);

       
    }

    public List<Employee> findDoctorsByDepartmentId(long id){

        
            
        List<Employee> employees = empRepo.findEmployeeByDepartmentId(id);
        List<Employee> doctors = new ArrayList<Employee>();
        //implement java foreach loop to iterate through arraylist
        for (Employee employee : employees) {
            if(employee.getDesignation().equals("doctor")){
                doctors.add(employee);
            }
        }

        return doctors;


    }

}
