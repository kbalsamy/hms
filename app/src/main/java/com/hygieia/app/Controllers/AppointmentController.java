package com.hygieia.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.AppointmentDto;
import com.hygieia.app.Services.AppointmentService;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class AppointmentController {

    @Autowired
    private AppointmentService appService;


    // @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<?> createApp(@ResponseBody AppointmentDto appApp){


    // }

    

    
    
}
