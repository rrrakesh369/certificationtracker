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

            if(empRequest.getExpiryDate().isBefore(empRequest.getIssuedDate())
            || empRequest.getExpiryDate().isEqual(empRequest.getIssuedDate())){
                throw new IllegalArgumentException("Expiry date must be after issued date");
            }

            Employee employee= new Employee();
             employee.setEmployeeId(empRequest.getEmployeeId());
             employee.setCertificationName(empRequest.getCertificationName());
             employee.setIssuedDate(empRequest.getIssuedDate());
             employee.setExpiryDate(empRequest.getExpiryDate());
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


    public EmpResponse updateByEmployeeId(EmpRequest request,String employeeId) {
              Optional<Employee> optionalEmployee=  employeeRepository.findByEmployeeId(employeeId);
            if(optionalEmployee.isEmpty()){
                log.info("Employee with given Id does not exist");
                throw new RuntimeException("Employee Not Found " + employeeId);
            }

           Employee employee= optionalEmployee.get();

        employee.setCertificationName(request.getCertificationName());
            employee.setIssuedDate(request.getIssuedDate());
            employee.setExpiryDate(request.getExpiryDate());
            employee.setStatus(request.getStatus());
            employeeRepository.save(employee);
            return EmployeeMapper.toResponse(employee);
    }

    public boolean deleteByEmployeeId(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(id);
        if (optionalEmployee.isEmpty()) {
            log.info("Employee Id given does not exist");
            throw new RuntimeException("Employee Not Found " + id);
        }
        Employee employee = optionalEmployee.get();
        employeeRepository.delete(employee);
        return true;
    }
}
