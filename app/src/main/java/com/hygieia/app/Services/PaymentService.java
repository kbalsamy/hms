package com.hygieia.app.Services;
import org.springframework.web.reactive.function.client.WebClient;


import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    // public void HandlePayment(){
    //     WebClient webClient = WebClient.create();

    //     // Define the URL of the API endpoint for external payment gateway
    //     String apiUrl = "https://jsonplaceholder.typicode.com/posts/1";

    //     // Make a GET request and retrieve the response
    //     String responseBody = webClient.get()
    //             .uri(apiUrl)
    //             .retrieve()
    //             .bodyToMono(String.class)
    //             .block(); // blocking for simplicity; in a real application, use reactive programming

    //     // Print the response body
    //     System.out.println("Response: " + responseBody);

       
    //     // status == 200
    //      // process the responses 
    //     //  1. update the appoinment -> status -> booked
    //         // 2. return appoinment object
    


    // }
    
}
