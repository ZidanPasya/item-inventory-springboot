package com.example.demo.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public Collection<? extends GrantedAuthority> getAuthorities(GrantedAuthority authority) {
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        grantedAuthority.add(authority);
        return grantedAuthority;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        String roleName = user.getRole().getName();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Set.of(authority));
    }
}
