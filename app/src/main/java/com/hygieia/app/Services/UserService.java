package com.hygieia.app.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserDto saveUser(UserRegisterDto userregDto) {
        User user = new User();
        user.setUserName(userregDto.getUserName());
        user.setUserEmail(userregDto.getUserEmail());
        user.setUserPassword(userregDto.getUserPassword());

        User usersaved=userRepo.save(user);
        UserDto userDto=new UserDto(usersaved);
        return userDto;
    }

    public Boolean Validate(String userName, String userPassword){

        List<User> users = userRepo.findAll();
        for (User user : users) {
            if(user.getUserName().equals(userName) &&
            user.getUserPassword().equals(userPassword)){
                return true;
            }
        }
            return false;
            
        }

        


    }

    // public User findByEmail(String email) {
    //     return userRepo.findByEmail(email);
    // }

