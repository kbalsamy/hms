package com.hygieia.app.DTO;

import com.hygieia.app.Models.AuthUser;

import lombok.*;

@Getter
@Setter

public class UserDto {
    
    private String userName;
    
    private String userEmail;
    
    private String userPassword;

    public UserDto(AuthUser user) {
      
        this.userName = user.getUserName();
        this.userPassword = user.getUserPassword();
 }

    
}
