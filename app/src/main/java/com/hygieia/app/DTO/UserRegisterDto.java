package com.hygieia.app.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    private String userName;

    private String userEmail;

    private String userPassword;
    
}
