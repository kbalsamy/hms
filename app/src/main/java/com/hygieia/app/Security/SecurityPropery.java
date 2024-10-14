package com.hygieia.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityPropery {

    private static final String API_URL_PATTERN = "api/v1/user/**";

    private JwtAuthorizationFilter JwtAuthorizationFilter;

    public SecurityPropery(JwtAuthorizationFilter JwtAuthorizationFilter) {
        this.JwtAuthorizationFilter = JwtAuthorizationFilter;
    }

    @Autowired
	private HygieiaUserDetailsService myuserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
       MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

       
        httpSecurity.cors(cor -> cor.disable()).csrf(cs -> cs.disable());
        // for h2 console to work
        httpSecurity.headers().frameOptions().sameOrigin();

        httpSecurity.authorizeHttpRequests(auth -> 

        (auth.requestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN)).permitAll()
                ).anyRequest().authenticated()
            
        ).addFilterBefore(JwtAuthorizationFilter,UsernamePasswordAuthenticationFilter.class);




        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public void Configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myuserDetailsService);

	}

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//  @Bean
//     public JwtParser jwtParser() {
//         return (JwtParser) Jwts.parser();
//     }



}
