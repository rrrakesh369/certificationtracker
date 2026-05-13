package com.certificationtracker.employee.Repository;

import com.certificationtracker.employee.Entity.Employee;
import com.certificationtracker.utils.CertificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
        boolean existsByEmployeeId(String employeeId);
        List<Employee> findByStatus(CertificationStatus status);
       Optional<Employee> findByEmployeeId(String id);

}
