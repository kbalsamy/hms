package com.hygieia.app.Controllers;


import org.apache.velocity.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.DepartmentDto;
import com.hygieia.app.Models.Department;
import com.hygieia.app.Repositories.DepartmentRepository;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.DepartmentService;







@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // private AppLogger logger = AppLogger.getInstance();

    // create a new department
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createDepartment(@RequestBody DepartmentDto departmentDto) {
        try{
            
            Department department = departmentService.saveDepartment(departmentDto);
            if (department==null) throw new Exception("Department already exists");
            // logger.logInfo("Department created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Department created successfully", department));
        }catch(Exception e){
            // logger.logError("Department creation failed", e);
            return ResponseEntity.ok(new ApiResponse(false, "Department creation failed", null));
        }    

    }

    //get all departments
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllDepartments() {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Departments fetched successfully", departmentService.getAllDepartments()));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Departments fetch failed", null));
        }
    }

     //update an existing department   
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateDepartment(@RequestBody Department department, @PathVariable Long id) {
        try{
            Department existingDepartment = departmentService.findDepartmentById(id).orElseThrow(()->new ResourceNotFoundException(
                    "Org Unit not found"));          
            existingDepartment.setName(department.getName());
            existingDepartment.setDescription(department.getDescription());
            departmentService.updateDepartment(existingDepartment);
            // logger.logInfo("Org Unit updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Org Unit updated successfully", existingDepartment));
        }catch(ResourceNotFoundException e){
            // logger.logError("Org Unit update failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    
    //TODO: add delete functionality later



    //get an org unit by id 

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDepartmentById(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Org Unit retrieved successfully", departmentService.findDepartmentById(id)));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error retrieving org unit", null));
        }
    }

    


}
