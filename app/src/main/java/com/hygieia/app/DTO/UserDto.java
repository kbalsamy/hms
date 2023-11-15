package com.hygieia.app.DTO;

import com.hygieia.app.Models.User;

import lombok.*;

@Getter
@Setter

public class UserDto {
    
    private int userId;
    
    private String userName;
    
    private String userEmail;
    
    private String userPassword;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userPassword = user.getUserPassword();
 }

    
}
