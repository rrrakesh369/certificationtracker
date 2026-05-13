package com.certificationtracker.employee.controller;

import com.certificationtracker.dto.request.EmpRequest;
import com.certificationtracker.dto.response.EmpResponse;
import com.certificationtracker.employee.Entity.Employee;
import com.certificationtracker.employee.service.EmployeeService;
import com.certificationtracker.utils.CertificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @GetMapping(path = "/{id}")
    public ResponseEntity<EmpResponse> getEmployeeById(@PathVariable("id") String id){
       EmpResponse empResponse= employeeService.getEmployeeById(id);
       return ResponseEntity.ok(empResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmpResponse> updateByEmployeeId(@RequestBody EmpRequest request,@PathVariable("id") String id){
       EmpResponse empResponse= employeeService.updateByEmployeeId(request,id);
       return ResponseEntity.ok(empResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteByEmployeeId(@PathVariable ("id") String id){
       boolean empResponse= employeeService.deleteByEmployeeId(id);
       return ResponseEntity.ok(empResponse);
    }
}
