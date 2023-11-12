package com.hygieia.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygieia.Models.User;
import com.hygieia.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User GetUserById(Integer id) throws Exception {
        var user = userRepository.findById(id);
        var newUser = new User();
        if (user.isEmpty()) {
            throw new Exception("User does not exist");            
        }
        BeanUtils.copyProperties(user.get(),newUser);
        return newUser;

    }

    public List<User> GetAllUsers() {
        var listOfUsers = new ArrayList<User>();
        var users = userRepository.findAll();
        for (com.hygieia.Entities.User x : users) {
            var user = new User();
            BeanUtils.copyProperties(x, user);
            listOfUsers.add(user);
        }
        return listOfUsers;
    }

    public void createUser(User user) {
        var newUser = new com.hygieia.Entities.User();
        BeanUtils.copyProperties(user, newUser);
        userRepository.save(newUser);
    }
}
