package com.hygieia.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hygieia.app.DTO.UserLogInDto;
import com.hygieia.app.Models.AuthUser;
import com.hygieia.app.Models.Role;
import com.hygieia.app.Security.JWTfunctionality;

@Service
public class TokenService {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTfunctionality Jwttokenutil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String GenerateToken(UserLogInDto userlog) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userlog.getUserName(),
                        userlog.getUserPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser user = authService.GetAuthUserByUserName(userlog.getUserName());
        Role role = user.getRole();
        List<String> permissions = role.getPermissions();
        String token = Jwttokenutil.GenerateToken(authentication, role.getRoleName(), permissions);

        return token;
    }

}
