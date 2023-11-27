package com.hygieia.app.Controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.EmployeeTypeDto;
import com.hygieia.app.Models.EmployeeType;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.EmployeeTypeService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employeetype")
public class EmployeeTypeController {


    @Autowired
    private EmployeeTypeService empTypeService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> Registration(@RequestBody EmployeeTypeDto empTypeDto) {

        try{
            
            EmployeeType empType = empTypeService.saveRole(empTypeDto);

            if(empType==null) throw new Exception("Employee Type already exists");
        //    System.out.println(role);
            // logger.logInfo("Department created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Role created successfully", empType));
        }catch(Exception e){
            // logger.logError("Department creation failed", e);
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));
        }    

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> getAllEmployeeTypes() {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Employee Types fetched successfully", empTypeService.getAllEmployeeTypes()));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Employee Types fetch failed", null));
        }
    }

    

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> getEmpTypeById(@PathVariable int id) {
        try{

            EmployeeType role = empTypeService.findEmpTypeNameById(id).orElseThrow(()->new ResourceNotFoundException(
                    "Employee Type found not found"));
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Org Unit retrieved successfully", role));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error retrieving role", null));
        }
    }


    
}
