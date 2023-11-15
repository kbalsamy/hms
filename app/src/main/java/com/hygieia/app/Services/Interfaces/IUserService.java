package com.hygieia.app.Services.Interfaces;

import com.hygieia.app.DTO.UserDto;
import com.hygieia.app.DTO.UserRegisterDto;

public interface IUserService {
    UserDto saveUser(UserRegisterDto userDto);
    Boolean Validate(String userName, String userPassword);

    
}
