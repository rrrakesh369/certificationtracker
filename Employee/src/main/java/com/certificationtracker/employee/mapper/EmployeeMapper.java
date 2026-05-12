package com.certificationtracker.employee.mapper;

import com.certificationtracker.dto.response.EmpResponse;
import com.certificationtracker.employee.Entity.Employee;
import com.certificationtracker.utils.CertificationStatus;

import java.time.LocalDate;


public class EmployeeMapper {

    public static EmpResponse toResponse(Employee employee){
       CertificationStatus status= employee.getStatus();

       if(employee.getIssuedDate().isBefore(LocalDate.now())) {
          status= CertificationStatus.EXPIRED;
       }
           EmpResponse empResponse = new EmpResponse();
           empResponse.setEmployeeId(employee.getEmployeeId());
           empResponse.setCertificationName(employee.getCertificationName());
           empResponse.setIssuedDate(employee.getIssuedDate().atStartOfDay());
           empResponse.setExpiryDate(employee.getExpiryDate().atStartOfDay());
           empResponse.setStatus(employee.getStatus());
           return empResponse;

    }

}
