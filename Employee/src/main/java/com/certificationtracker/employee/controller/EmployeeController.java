package com.certificationtracker.employee.controller;

import com.certificationtracker.dto.request.EmpRequest;
import com.certificationtracker.dto.response.EmpResponse;
import com.certificationtracker.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certifications")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<EmpResponse> createEmployee(@RequestBody EmpRequest empRequest){
       EmpResponse empResponse= employeeService.createEmployee(empRequest);
        return ResponseEntity.ok().header("CREATED", "200").body(empResponse);
    }
}
