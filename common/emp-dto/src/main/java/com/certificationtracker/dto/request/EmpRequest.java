package com.certificationtracker.dto.request;

import com.certificationtracker.utils.CertificationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmpRequest {

    @NotBlank
    private String employeeId;
    private String certificationName;
    private String issuedDate;
    private String expiryDate;
    private CertificationStatus status;
 }
