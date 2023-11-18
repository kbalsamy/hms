package com.hygieia.app.Controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.AvailabilityDto;
import com.hygieia.app.Models.Availability;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.AvailabilityService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    // create availability
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAvailability(@RequestBody AvailabilityDto availabilityDto) {
        try{
            
            Availability availability = availabilityService.saveAvailability(availabilityDto);
            return ResponseEntity.ok(new ApiResponse(true, "Availability created successfully", availability));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Availability creation failed", null));
        }


    }

    // update availability

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAvailability(@RequestBody AvailabilityDto availabilityDto, @PathVariable int id) {
        try{

            Availability existingAvailability = availabilityService.findById(id).orElseThrow(()->new ResourceNotFoundException(
                    "Availability not found"));

            Availability updatedAvailability = availabilityService.updateAvailability(existingAvailability, availabilityDto);
            return ResponseEntity.ok(new ApiResponse(true, "Availability updated successfully", updatedAvailability));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Availability update failed", null));
        }
    }

    // get all availabilities

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAvailabilities() {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Availabilities fetched successfully", availabilityService.getAllAvailabilities()));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Availabilities fetch failed", null));
        }
    }

    // get availability by id

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAvailabilityById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Availability fetched successfully", availabilityService.findById(id)));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Availability fetch failed", null));
        }
    }

    // get availability by doctor id

    @GetMapping("/get/doctor/{id}")
    public ResponseEntity<ApiResponse> getAvailabilityByDoctorId(@PathVariable int id) {
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Availability fetched successfully", availabilityService.findAvailabilityByDoctorId(id)));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Availability fetch failed", null));
        }
    }
    
}
