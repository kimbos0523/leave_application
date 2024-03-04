package com.tekbasic.service;

import com.tekbasic.domain.Employee;
import com.tekbasic.domain.Manager;
import com.tekbasic.dto.MemberForm;
import com.tekbasic.repository.EmployeeRepository;
import com.tekbasic.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;


    public void saveMemberForm(MemberForm memberForm) {
        String role = memberForm.getRole();
        if (role.equals("employee")) {
            Employee employee = memberForm.toEmployee();
            employeeRepository.save(employee);
        } else if (role.equals("manager")) {
            Manager manager = memberForm.toManager();
            managerRepository.save(manager);
        } else {
            throw new IllegalArgumentException("Role should be either employee or manager");
        }
    }
}
