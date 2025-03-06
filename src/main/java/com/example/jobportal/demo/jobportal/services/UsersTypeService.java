package com.example.jobportal.demo.jobportal.services;

import com.example.jobportal.demo.jobportal.entity.UsersType;
import com.example.jobportal.demo.jobportal.repository.UsersTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {
    @Autowired
    private final UsersTypeRepository usersTypeRepository;
    @Autowired
    public UsersTypeService(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAll() {
        return usersTypeRepository.findAll();
    }
}