package com.example.jobportal.demo.jobportal.services;

import com.example.jobportal.demo.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.demo.jobportal.entity.RecruiterProfile;
import com.example.jobportal.demo.jobportal.entity.Users;
import com.example.jobportal.demo.jobportal.repository.JobSeekerProfileRepository;
import com.example.jobportal.demo.jobportal.repository.UsersRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileService {
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UsersRepository usersRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository, UsersRepository usersRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }


    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    public JobSeekerProfile getCurrentSeekerProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            // Fetch the seeker profile based on the user's ID
            Optional<JobSeekerProfile> seekerProfile = getOne(users.getUserId());
            // If no seeker profile found, throw an exception or return an empty profile
            return seekerProfile.orElse(null);
        } else return null;
    }
}
