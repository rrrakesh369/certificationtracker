package com.certificationtracker.employee.controller;

import com.certificationtracker.dto.request.EmpRequest;
import com.certificationtracker.dto.response.EmpResponse;
import com.certificationtracker.employee.service.EmployeeService;
import com.certificationtracker.utils.CertificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<EmpResponse>> getAllEmployee(@RequestParam(value = "status", required = false) CertificationStatus status){
              List<EmpResponse> empResponses=  employeeService.getAllEmployee(status);
                return ResponseEntity.ok(empResponses);

    }
}
