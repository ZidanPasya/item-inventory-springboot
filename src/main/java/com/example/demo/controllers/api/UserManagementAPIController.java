package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dtos.UserManagementDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.UserManagementService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class UserManagementAPIController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserManagementService userManagementService;

    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/register")
    public String register(@RequestBody UserManagementDTO userManagementDTO) {
        if (userRepository.existsByUsername(userManagementDTO.getUsername())) {
            return "Error: Username is already taken!";
        }
        userManagementService.save(userManagementDTO);

        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody UserManagementDTO userManagementDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userManagementDTO.getUsername(),
                        userManagementDTO.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);
        return jwtUtils.generateToken(userDetails.getUsername(), role);
    }

}
