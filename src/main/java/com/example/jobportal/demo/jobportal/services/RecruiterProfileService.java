package com.example.jobportal.demo.jobportal.services;

import com.example.jobportal.demo.jobportal.entity.RecruiterProfile;
import com.example.jobportal.demo.jobportal.entity.Users;
import com.example.jobportal.demo.jobportal.repository.RecruiterProfileRepository;
import com.example.jobportal.demo.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository, UsersRepository usersRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.usersRepository = usersRepository;
    }
    //Optional<T> is a container object which may or may not contain a non-null value of type T
    //The idea is to provide a safer way to deal with values that could potentially be null, helping to avoid NullPointerException.
    public Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            // Fetch the recruiter profile based on the user's ID
            Optional<RecruiterProfile> recruiterProfile = getOne(users.getUserId());
            // If no recruiter profile found, throw an exception or return an empty profile
            return recruiterProfile.orElse(null);
        } else return null;

    }
}
