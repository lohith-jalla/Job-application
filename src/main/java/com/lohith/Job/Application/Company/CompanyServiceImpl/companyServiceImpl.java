package com.lohith.Job.Application.Company.CompanyServiceImpl;

import com.lohith.Job.Application.Company.Company;
import com.lohith.Job.Application.Company.CompanyRepository;
import com.lohith.Job.Application.Company.CompanyService;
import com.lohith.Job.Application.Job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;


@Service
public class companyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public companyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean UpdateCompany(Long id, Company company) {
        Company companyToUpdate=companyRepository.findById(id).orElse(null);
        if(companyToUpdate!=null){
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setJobs(company.getJobs());

            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void CreateCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<Job> getAllJobsByCompanyId(Long id) {
        Company company=companyRepository.findById(id).orElse(null);
        if(company!=null){
            return company.getJobs();
        }
        return null;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompany(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }
}
