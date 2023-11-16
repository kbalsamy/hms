package com.hygieia.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.hygieia.app.DTO.AuthResponseDto;
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
    private JWTfunctionality Jwttokenutil;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> Registration(@RequestBody UserRegisterDto user) {

        UserDto userdto = userService.saveUser(user);

        if (userdto == null)

            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Successfully Registered", HttpStatus.OK);
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity<AuthResponseDto> Login(@RequestBody UserLogInDto userlog) throws Exception {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userlog.getUserName(),
                            userlog.getUserPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String jwt = Jwttokenutil.GenerateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDto(jwt), HttpStatus.BAD_REQUEST);

        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new AuthResponseDto("incorrect username /password"), HttpStatus.OK);

        }

    }

}
