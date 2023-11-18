package com.hygieia.app.Services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.hygieia.app.Models.AuthUser;
import com.hygieia.app.Repositories.AuthUserRepository;

@Service
public class AuthService {

    @Autowired
    AuthUserRepository userRepo;

    // @Autowired
    // PasswordEncoder passwordEncoder;

    public User GetUserByUserName(String userName) {

        com.hygieia.app.Models.AuthUser user = userRepo.GetuserbyUserName(userName);

        User userDet = null;

        if (user != null) {
            ArrayList<GrantedAuthority> newAuthorities = new ArrayList<>();

            newAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
            userDet = new User(user.getUserName(), user.getUserPassword(), newAuthorities);
        }

        return userDet;

    }

    public AuthUser createAuthUser(AuthUser user) {

       // user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        AuthUser authUser = userRepo.save(user);

        return authUser;

    }
}
