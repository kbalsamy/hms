package com.hygieia.app.Services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.hygieia.app.Repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepo;

   public User GetUserByUserName(String userName){

            com.hygieia.app.Models.User user=userRepo.GetuserbyUserName(userName);

            User userDet=null;

            if(user!=null){
                ArrayList<GrantedAuthority> newAuthorities = new ArrayList<>();
                
		
				newAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
                userDet=new User(user.getUserName(),user.getUserPassword(),newAuthorities);
            }

            return userDet;


        }
}
