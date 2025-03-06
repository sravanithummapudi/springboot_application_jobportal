package com.example.jobportal.demo.jobportal.services;

import com.example.jobportal.demo.jobportal.entity.Users;
import com.example.jobportal.demo.jobportal.repository.UsersRepository;
import com.example.jobportal.demo.jobportal.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  

    private final UsersRepository usersRepository;
    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=  usersRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("couldn't find user"));
        return new CustomUserDetails(user);
    }
}
