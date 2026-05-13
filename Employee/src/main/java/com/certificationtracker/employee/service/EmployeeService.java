package com.certificationtracker.employee.service;

import com.certificationtracker.dto.request.EmpRequest;
import com.certificationtracker.dto.response.EmpResponse;
import com.certificationtracker.employee.Entity.Employee;
import com.certificationtracker.employee.Repository.EmployeeRepository;
import com.certificationtracker.employee.mapper.EmployeeMapper;
import com.certificationtracker.utils.CertificationStatus;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public List<EmpResponse> getAllEmployee(CertificationStatus status) {

        List<Employee> employees =
                employeeRepository.findByStatus(status);

        if (employees.isEmpty()) {
            log.info("Status not found");
            return Collections.emptyList();
        }

        List<EmpResponse> responseList = new ArrayList<>();

        for (Employee employee : employees) {

            if (employee.getStatus() == CertificationStatus.ACTIVE) {

                EmpResponse empResponse = new EmpResponse();

                empResponse.setEmployeeId(employee.getEmployeeId());

                empResponse.setIssuedDate(employee.getIssuedDate().atStartOfDay());

                empResponse.setExpiryDate(employee.getExpiryDate().atStartOfDay());

                empResponse.setCertificationName(employee.getCertificationName());

                empResponse.setStatus(employee.getStatus());

                responseList.add(empResponse);
            }
        }

        return responseList;
    }

    public EmpResponse getEmployeeById(String id) {
       Optional<Employee> employee= employeeRepository.findByEmployeeId(id);
       if(employee.isEmpty()){
           log.info("Employee is not Available");
       }
      Employee employee1= employee.get();

        EmpResponse empResponse= new EmpResponse();
        empResponse.setEmployeeId(employee1.getEmployeeId());
        empResponse.setIssuedDate(employee1.getIssuedDate().atStartOfDay());
        empResponse.setExpiryDate(employee1.getExpiryDate().atStartOfDay());
        empResponse.setCertificationName(employee1.getCertificationName());
        empResponse.setStatus(employee1.getStatus());
        return empResponse;
    }
}
