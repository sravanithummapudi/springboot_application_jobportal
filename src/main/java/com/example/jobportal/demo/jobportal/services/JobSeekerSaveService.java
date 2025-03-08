package com.example.jobportal.demo.jobportal.services;

import com.example.jobportal.demo.jobportal.entity.JobPostActivity;
import com.example.jobportal.demo.jobportal.entity.JobSeekerApply;
import com.example.jobportal.demo.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.demo.jobportal.entity.JobSeekerSave;
import com.example.jobportal.demo.jobportal.repository.JobSeekerSaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerSaveService {
    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
    }

    public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId){
        return jobSeekerSaveRepository.findByUserId(userAccountId);

    }

    public List<JobSeekerSave> getJobCandidates(JobPostActivity job){
        return jobSeekerSaveRepository.findByJob(job);

    }


    public void addNew(JobSeekerSave jobSeekerSave) {
        jobSeekerSaveRepository.save(jobSeekerSave);
    }
}
