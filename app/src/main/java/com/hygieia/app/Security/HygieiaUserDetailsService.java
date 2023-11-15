package com.hygieia.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hygieia.app.Services.AuthService;

@Service
public class HygieiaUserDetailsService implements UserDetailsService{
    @Autowired
	private AuthService authService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return authService.GetUserByUserName(username);
	}


    
}
