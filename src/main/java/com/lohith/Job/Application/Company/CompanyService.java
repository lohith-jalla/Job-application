package com.lohith.Job.Application.Company;

import com.lohith.Job.Application.Job.Job;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean UpdateCompany(Long id,Company company);
    void CreateCompany(Company company);
    List<Job> getAllJobsByCompanyId(Long id);
    Company getCompanyBuId(Long id);
    boolean deleteCompany(Long id);
}
