package com.hygieia.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hygieia.app.DTO.UserDto;
import com.hygieia.app.DTO.UserLogInDto;
import com.hygieia.app.DTO.UserRegisterDto;
import com.hygieia.app.Security.JWTfunctionality;
import com.hygieia.app.Services.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
	private UserDetailsService userdetailsservice;

    @Autowired
	private JWTfunctionality Jwttokenutil;
    

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> Registration(@RequestBody UserRegisterDto user) {

        UserDto userdto = userService.saveUser(user);

        if (userdto == null)

            return ResponseEntity.ok().body("Invalid Email/User already exist");

        return ResponseEntity.ok().body("Successfully Registered");
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity<?> Login(@RequestBody UserLogInDto userlog) throws Exception {

        String userName = userlog.getUserName();
        String userPass = userlog.getUserPassword();

        Boolean res = userService.Validate(userName, userPass);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userlog.getUserName(), userlog.getUserPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username and password");
        }

        final UserDetails userdet=userdetailsservice.loadUserByUsername(userName);
        final String jwt = Jwttokenutil.GenerateToken(userdet);

        if (res) {
            return ResponseEntity.ok(jwt);

        }
        return ResponseEntity.ok("Invalid Credentials");

    }

}
