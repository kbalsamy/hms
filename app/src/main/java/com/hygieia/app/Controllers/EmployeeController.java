package com.hygieia.app.Controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.EmployeeRegDto;
import com.hygieia.app.Models.AuthUser;
import com.hygieia.app.Models.Employee;
import com.hygieia.app.Models.Role;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.AuthService;
import com.hygieia.app.Services.EmployeeService;
import com.hygieia.app.Services.PasswordService;
import com.hygieia.app.Services.RoleService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordService passwordService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> Registration(@RequestBody EmployeeRegDto empDto) {

        try {

            Employee emp = employeeService.saveUser(empDto);
            if (emp == null)
                throw new Exception("Employee already exists");
            // get role for the user
            Role role = roleService.findRoleByRoleName(empDto.getRole()).get();

            // create authuser record
            if (emp == null || role == null)
                throw new Exception("Unable to create user");
            AuthUser authUser = new AuthUser();
            authUser.setUserId(emp.getId());
            authUser.setUserName(emp.getUserName());
            authUser.setUserPassword(passwordService.EncodePassword(empDto.getUserPassword()));
            authUser.setRole(role);
            authService.createAuthUser(authUser);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Employee created successfully", emp));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));
        }

    }

      @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> GetEmployeeById(@PathVariable int id) {

        try {

            Employee pat = employeeService.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException(
                    "Patient not found"));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(true, "Org Unit retrieved successfully", pat));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error retrieving patient", null));
        }
    }


    @GetMapping("/find")
    public ResponseEntity<ApiResponse> GetdoctorsyDepartmentID(@RequestParam Long depatmentId) {

        try {

            List<Employee> employees = employeeService.findDoctorsByDepartmentId(depatmentId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(true, "Doctors retrieved successfully", employees));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error retrieving doctors", null));
        }
    }

}
