package com.example.jobportal.demo.jobportal.services;

import com.example.jobportal.demo.jobportal.entity.*;
import com.example.jobportal.demo.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }


    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){
        //convert info from database to dto's
        List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);
          //transforming the data returned from the jobPostActivityRepository.getRecruiterJobs(recruiter) query into a list of RecruiterJobsDto objects.
        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

        for (IRecruiterJobs rec : recruiterJobsDtos) {
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(),
                    rec.getJob_title(), loc, comp));
        }
        return recruiterJobsDtoList;


    }

    public JobPostActivity getOne(int id) {

        return jobPostActivityRepository.findById(id).orElseThrow(()->new RuntimeException("Job not found"));
    }

    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();
    }

    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {
        //Objects.isNull(searchDate) checks if the searchDate is null. If searchDate is null, it means we don't want
        // to filter the job posts by a specific date,
        // and we can retrieve all job posts regardless of when they were posted.
        return Objects.isNull(searchDate)?jobPostActivityRepository.searchWithoutDate(job,location,remote,type):
                jobPostActivityRepository.search(job,location,remote,type,searchDate);
    }
}
