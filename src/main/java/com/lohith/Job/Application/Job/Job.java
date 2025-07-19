package com.lohith.Job.Application.Job;

import com.lohith.Job.Application.Company.Company;
import com.lohith.Job.Application.Status;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Status status;

    @ManyToOne
    private Company company;
}
