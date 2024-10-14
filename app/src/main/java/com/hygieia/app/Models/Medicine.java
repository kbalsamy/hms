package com.hygieia.app.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {


    private String name;

    private float quantity;

    private Dosage doasage;
    
}
