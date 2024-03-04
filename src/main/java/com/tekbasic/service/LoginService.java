package com.tekbasic.service;

import com.tekbasic.domain.Employee;
import com.tekbasic.domain.Manager;
import com.tekbasic.repository.EmployeeRepository;
import com.tekbasic.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;


    public Employee loginEmployee(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    public Manager loginManager(String email, String password) {
        return managerRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
