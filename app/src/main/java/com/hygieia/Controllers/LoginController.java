package com.hygieia.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hygieia.Models.User;
import com.hygieia.Services.UserService;

@RestController
@CrossOrigin
@RequestMapping(value="/api/user")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<?> getUsers(){
        var users = userService.GetAllUsers();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(users);

    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody @Validated User user) {
        userService.createUser(user);
        return ResponseEntity.accepted().body(user);
    }
}
