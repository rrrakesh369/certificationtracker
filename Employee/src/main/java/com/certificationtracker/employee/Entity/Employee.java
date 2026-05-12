package com.certificationtracker.employee.Entity;

import com.certificationtracker.utils.CertificationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    @Column(unique = true)
    private String employeeId;

    private String certificationName;

    private LocalDate issuedDate;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private CertificationStatus status;

}
