package com.hygieia.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hygieia.app.DTO.UserDto;
import com.hygieia.app.DTO.UserRegisterDto;
import com.hygieia.app.Models.User;
import com.hygieia.app.Repositories.*;
import com.hygieia.app.Services.Interfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto saveUser(UserRegisterDto userregDto) {

        if(userRepo.existsByUserName(userregDto.getUserName())){
            return null;
        }
        User user = new User();
        user.setUserName(userregDto.getUserName());
        user.setUserEmail(userregDto.getUserEmail());
        user.setUserPassword(passwordEncoder.encode(userregDto.getUserPassword()));

        User usersaved = userRepo.save(user);
        UserDto userDto = new UserDto(usersaved);
        return userDto;
    }

}
