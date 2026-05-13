package com.certificationtracker.employee.service;

import com.certificationtracker.dto.request.EmpRequest;
import com.certificationtracker.dto.response.EmpResponse;
import com.certificationtracker.employee.Entity.Employee;
import com.certificationtracker.employee.Repository.EmployeeRepository;
import com.certificationtracker.employee.mapper.EmployeeMapper;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmpResponse createEmployee(EmpRequest empRequest) {

        boolean existsById= employeeRepository.existsByEmployeeId(empRequest.getEmployeeId());
            if(existsById){
                log.info("Employee is Available");
                throw new DuplicateRequestException("Employee is not Exists" + empRequest.getEmployeeId());
            }

            Employee employee= new Employee();
             employee.setEmployeeId(empRequest.getEmployeeId());
             employee.setCertificationName(empRequest.getCertificationName());
             employee.setIssuedDate(LocalDate.parse(empRequest.getIssuedDate()));
             employee.setExpiryDate(LocalDate.parse(empRequest.getExpiryDate()));
             employee.setStatus(empRequest.getStatus());
            employeeRepository.save(employee);
            log.info("Employee {} is save", employee.getEmployeeId());
            return EmployeeMapper.toResponse(employee);
    }
}
