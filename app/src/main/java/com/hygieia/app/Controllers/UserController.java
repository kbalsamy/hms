
// This package handles User registration, login and logout.
package com.hygieia.app.Controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hygieia.app.DTO.AuthResponseDto;
import com.hygieia.app.DTO.UserLogInDto;
import com.hygieia.app.DTO.UserRegisterDto;
import com.hygieia.app.Models.AuthUser;
import com.hygieia.app.Models.Patient;
import com.hygieia.app.Models.Role;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.AuthService;
import com.hygieia.app.Services.PasswordService;
import com.hygieia.app.Services.PatientService;
import com.hygieia.app.Services.RoleService;
import com.hygieia.app.Services.TokenService;
import com.hygieia.app.Services.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private TokenService tokenService;

    // patient registration api
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> Registration(@RequestBody UserRegisterDto user) {

        try {
            Role role = roleService.findRoleByRoleName("patient").get();
            if (role == null)
                throw new Exception("Role not found");

            Patient pat = userService.saveUser(user);
            if (pat == null)
                throw new Exception("user already exists");
            // get role for the user
            // create authuser record
            if (pat == null || role == null)
                throw new Exception("Unable to create user");
            AuthUser authUser = new AuthUser();
            authUser.setUserId(pat.getId());
            authUser.setUserName(user.getUserName());
            authUser.setUserPassword(passwordService.EncodePassword(user.getUserPassword()));
            authUser.setRole(role);
            authService.createAuthUser(authUser);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "user created successfully", pat));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));
        }

    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity<AuthResponseDto> Login(@RequestBody UserLogInDto userlog) throws Exception {

        try {
            final String jwt = tokenService.GenerateToken(userlog);
            return new ResponseEntity<>(new AuthResponseDto(jwt), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(new AuthResponseDto("incorrect username /password"), HttpStatus.UNAUTHORIZED);

        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> GetPatientById(@PathVariable int id) {

        try {

            Patient pat = patientService.findPatientById(id).orElseThrow(() -> new ResourceNotFoundException(
                    "Patient not found"));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(true, "Patient retrieved successfully", pat));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error retrieving Patient", null));
        }
    }

}
