package com.example.jobportal.demo.jobportal.controller;

import com.example.jobportal.demo.jobportal.entity.JobPostActivity;
import com.example.jobportal.demo.jobportal.entity.JobSeekerProfile;
import com.example.jobportal.demo.jobportal.entity.JobSeekerSave;
import com.example.jobportal.demo.jobportal.entity.Users;
import com.example.jobportal.demo.jobportal.services.JobPostActivityService;
import com.example.jobportal.demo.jobportal.services.JobSeekerProfileService;
import com.example.jobportal.demo.jobportal.services.JobSeekerSaveService;
import com.example.jobportal.demo.jobportal.services.UsersService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class JobSeekerSaveController {
    private final UsersService usersService;
    private final JobSeekerSaveService jobSeekerSaveService;
    private final JobPostActivityService jobPostActivityService;
    private final JobSeekerProfileService jobSeekerProfileService;

    public JobSeekerSaveController(UsersService usersService, JobSeekerSaveService jobSeekerSaveService, JobPostActivityService jobPostActivityService, JobSeekerProfileService jobSeekerProfileService) {
        this.usersService = usersService;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.jobPostActivityService = jobPostActivityService;
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @PostMapping("job-details/save/{id}")

    public String save(@PathVariable("id") int id, JobSeekerSave jobSeekerSave) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users user = usersService.findByEmail(currentUsername);
            Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
            //Fetching the current logged-in user's profile and the job posting the user wants to save.
            JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
            if (seekerProfile.isPresent() && jobPostActivity != null) {
                jobSeekerSave.setJob(jobPostActivity);
                jobSeekerSave.setUserId(seekerProfile.get());
            } else {
                throw new RuntimeException("User not found");
            }
            //ecording that the job seeker has saved the job posting.
            jobSeekerSaveService.addNew(jobSeekerSave);
        }
        return "redirect:/dashboard/";
    }


    @GetMapping("saved-jobs/")
    public String savedJobs(Model model) {

        List<JobPostActivity> jobPost = new ArrayList<>();
        Object currentUserProfile = usersService.getCurrentUserProfile();
        // It  is used to fetch a list of saved jobs for the current user, which is stored in jobSeekerSaveList.
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getCandidatesJob((JobSeekerProfile) currentUserProfile);
        for (JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
            jobPost.add(jobSeekerSave.getJob());
        }

        model.addAttribute("jobPost", jobPost);
        model.addAttribute("user", currentUserProfile);

        return "saved-jobs";
    }

}
