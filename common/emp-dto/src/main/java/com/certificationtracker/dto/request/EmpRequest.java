package com.certificationtracker.dto.request;

import com.certificationtracker.utils.CertificationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpRequest {

    @NotBlank
    private String employeeId;
    private String certificationName;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
    private CertificationStatus status;
 }
