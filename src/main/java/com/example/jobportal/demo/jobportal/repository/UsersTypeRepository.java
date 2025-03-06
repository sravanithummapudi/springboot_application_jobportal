package com.example.jobportal.demo.jobportal.repository;

import com.example.jobportal.demo.jobportal.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersTypeRepository extends JpaRepository<UsersType,Integer> {
}
