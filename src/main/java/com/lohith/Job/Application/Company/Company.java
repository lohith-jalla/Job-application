package com.lohith.Job.Application.Company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lohith.Job.Application.Job.Job;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    @JsonIgnore
    private List<Job> jobs;

}
