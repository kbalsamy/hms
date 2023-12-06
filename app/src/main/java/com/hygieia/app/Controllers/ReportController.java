package com.hygieia.app.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.DTO.ReportDto;
import com.hygieia.app.DTO.ReportResponseDto;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.ReportService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody ReportDto reportDto) {

        try{
           ReportResponseDto rep=reportService.CreateReport(reportDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Report created successfully", rep));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));

        }

    }
    
}
