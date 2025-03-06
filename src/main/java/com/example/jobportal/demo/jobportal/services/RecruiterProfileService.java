package com.example.jobportal.demo.jobportal.services;

import com.example.jobportal.demo.jobportal.entity.RecruiterProfile;
import com.example.jobportal.demo.jobportal.repository.RecruiterProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
    }
    //Optional<T> is a container object which may or may not contain a non-null value of type T
    //The idea is to provide a safer way to deal with values that could potentially be null, helping to avoid NullPointerException.
    public Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }
}
