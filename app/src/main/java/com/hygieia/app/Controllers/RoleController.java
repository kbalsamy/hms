package com.hygieia.app.Controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.RoleDto;
import com.hygieia.app.Models.Role;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.RoleService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/role")
public class RoleController {

     @Autowired
    private RoleService roleService;

    
    // create a new department
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> createRole(@RequestBody RoleDto roleDto) {
        try{
            
            Role role = roleService.saveRole(roleDto);

            if(role==null) throw new Exception("Role already exists");
        //    System.out.println(role);
            // logger.logInfo("Department created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Role created successfully", role));
        }catch(Exception e){
            // logger.logError("Department creation failed", e);
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));
        }    

    }

    //get all departments
    @GetMapping("/all")    
    public ResponseEntity<ApiResponse> getAllRoles() {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Departments fetched successfully", roleService.getAllRoles()));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Departments fetch failed", null));
        }
    }

     //update an existing department   
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> updateRoles(@RequestBody RoleDto roleDto, @PathVariable int id) {
        try{
            Role existingRole = roleService.findRoleById(id).orElseThrow(()->new ResourceNotFoundException(
                    "Org Unit not found"));
            existingRole.setRoleName(roleDto.getRoleName());
            existingRole.setPermissions(roleDto.getPermissions());

            RoleDto updatedDto=new RoleDto();

            updatedDto.setRoleName(existingRole.getRoleName());
            updatedDto.setPermissions(existingRole.getPermissions());

                       
            roleService.updateRole(existingRole);
            // logger.logInfo("Org Unit updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "role updated successfully", existingRole));
        }catch(ResourceNotFoundException e){
            // logger.logError("Org Unit update failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getRoleById(@PathVariable int id) {
        try{

            Role role = roleService.findRoleById(id).orElseThrow(()->new ResourceNotFoundException(
                    "Role not found"));
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Org Unit retrieved successfully", role));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error retrieving role", null));
        }
    }

     @GetMapping("get/{name}")
    public ResponseEntity<ApiResponse> getRoleByName(@PathVariable String name) {
        try{

            Role role = roleService.findRoleByRoleName(name).orElseThrow(()->new ResourceNotFoundException(
                    "Role not found"));
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Org Unit retrieved successfully", role));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error retrieving role", null));
        }
    }
    
}
