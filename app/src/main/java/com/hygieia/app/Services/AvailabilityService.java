package com.hygieia.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.AvailabilityDto;
import com.hygieia.app.Models.Availability;
import com.hygieia.app.Models.Employee;
import com.hygieia.app.Models.Role;
import com.hygieia.app.Repositories.AvailabilityRepository;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private EmployeeService employeeService;

    public Availability saveAvailability(AvailabilityDto availabilityDto) {


        // get doctor by id
        Employee doctor = employeeService.findEmployeeById(availabilityDto.getEmployee()).get();

        if (doctor == null) {
            new Exception("Doctor not found");
        }

        Availability availability = new Availability();
        availability.setDate(availabilityDto.getDate());
        availability.setFromTime(availabilityDto.getFromTime());
        availability.setToTime(availabilityDto.getToTime());
        availability.setEmployee(doctor);

        Availability newAvailability = availabilityRepository.save(availability);
        return newAvailability;
    }

    public Availability updateAvailability(Availability availability, AvailabilityDto availabilityDto) {

        
        availability.setDate(availabilityDto.getDate());
        availability.setFromTime(availabilityDto.getFromTime());
        availability.setToTime(availabilityDto.getToTime());
        
        if (availability.getEmployee().getId() != availabilityDto.getEmployee()) {
            Employee doctor = employeeService.findEmployeeById(availabilityDto.getEmployee()).get();

            if (doctor == null) {
                new Exception("Doctor not found");
            }

            availability.setEmployee(doctor);
        }

        availabilityRepository.save(availability);

        return availability;
    }

    public List<Availability> getAllAvailabilities(){


        List<Availability> availabilities=availabilityRepository.findAll();

        return availabilities;

    }

    public Optional<Availability> findById(int id){

        return availabilityRepository.findById(id);

       
    }

    public List<Availability> findAvailabilityByDoctorId(int id){

        return availabilityRepository.findAvailabiltyByEmployeeId(id);

       
    }






    
    
}
