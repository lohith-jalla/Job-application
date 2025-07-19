package com.lohith.Job.Application.Job.JobServiceImpl;


import com.lohith.Job.Application.Job.Job;
import com.lohith.Job.Application.Job.JobRepository;
import com.lohith.Job.Application.Job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean Update(Long id,Job job) {
        Job jobToUpdate = jobRepository.findById(id).orElse(null);
        if (jobToUpdate != null) {
            jobToUpdate.setName(job.getName());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setStatus(job.getStatus());
            jobToUpdate.setLocation(job.getLocation());
            jobToUpdate.setCompany(job.getCompany());

            jobRepository.save(jobToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Job jobToDelete = jobRepository.findById(id).orElse(null);
        if(jobToDelete != null) {
            try {
                jobRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
