package com.tekbasic.service;

import com.tekbasic.domain.Employee;
import com.tekbasic.domain.Manager;
import com.tekbasic.repository.EmployeeRepository;
import com.tekbasic.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;


    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Manager> findAllManager() {
        return managerRepository.findAll();
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findByName(String employeeName) {
        return employeeRepository.findByName(employeeName);
    }
}
