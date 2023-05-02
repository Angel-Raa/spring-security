package com.caja.ideal.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Validated
@RestController
@RequestMapping("/auth")
public class JwtController {
    @Autowired
    private JwtService service;
    @Autowired
    private AuthenticationManager manager;
    @PostMapping("/add")
    public String getTokenForAuthenticateUser(@RequestBody JwtAuthenticationRequest authRequest){
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            return service.getGeneratedToken(authRequest.getUsername());
        }
        else {
            throw new UsernameNotFoundException("Invalid user Credentials");
        }

    }
}
