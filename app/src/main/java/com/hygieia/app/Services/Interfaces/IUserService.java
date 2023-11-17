package com.hygieia.app.Services.Interfaces;

import com.hygieia.app.DTO.UserDto;
import com.hygieia.app.DTO.UserRegisterDto;

public interface IUserService {
    UserDto savePatient(UserRegisterDto userDto);
}
