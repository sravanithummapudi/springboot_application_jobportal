package com.example.jobportal.demo.jobportal.repository;

import com.example.jobportal.demo.jobportal.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile,Integer> {
}