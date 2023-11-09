package com.hygieia.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.Repositories.UserRepository;

@RestController
@CrossOrigin
@RequestMapping(value="/api/user")
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(path="/get", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(){
        var user = userRepository.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);

    }
}
