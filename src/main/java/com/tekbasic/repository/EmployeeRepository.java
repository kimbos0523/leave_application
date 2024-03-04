package com.tekbasic.repository;

import com.tekbasic.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByName(String employeeName);

    Optional<Employee> findByEmailAndPassword(String email, String password);

}
