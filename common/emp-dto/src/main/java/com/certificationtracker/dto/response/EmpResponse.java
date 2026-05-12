package com.certificationtracker.dto.response;

import com.certificationtracker.utils.CertificationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmpResponse {

   private String employeeId;

    private String certificationName;

    private LocalDateTime issuedDate;

    private LocalDateTime expiryDate;

     private CertificationStatus status;
}
