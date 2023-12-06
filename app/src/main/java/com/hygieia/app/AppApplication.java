package com.hygieia.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.hygieia.AppLogger.AppLogger;
import com.hygieia.app.Services.PatientService;
import com.hygieia.app.Services.PaymentService;
import com.hygieia.app.Services.ReportService;
import com.hygieia.app.Services.Interfaces.IPortal;
import com.hygieia.app.Services.Observers.Bill;
import com.hygieia.app.Services.Observers.PatientPortal;
import com.hygieia.app.Services.Observers.PharmacyPortal;

import jakarta.annotation.PostConstruct;

@SpringBootApplication( exclude = { SecurityAutoConfiguration.class })

public class AppApplication {

	public AppLogger logger;

	@Autowired
	PaymentService paymentService;

	@Autowired
	ReportService reportService;

	@Autowired
	PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@PostConstruct
	public void initLogger() {
		//initialize logger here
		logger = AppLogger.getInstance();
		logger.logInfo("Logger initialized");
		// attach observers here
		Bill bill = new Bill(patientService);		
		
		paymentService.attach(bill);

		//attach observers
		IPortal patPortal=new PatientPortal();
	    IPortal pharmacyPortal=new PharmacyPortal();

		reportService.attach(patPortal);
		reportService.attach(pharmacyPortal);

	}

	

}
