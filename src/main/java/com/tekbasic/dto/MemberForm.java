package com.tekbasic.dto;

import com.tekbasic.domain.Employee;
import com.tekbasic.domain.Manager;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberForm {

    private String role;
    private String email;
    private String password;
    private String name;
    private String position;
    private int salary;


    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setName(name);
        employee.setPosition(position);
        employee.setSalary(salary);
        return employee;
    }

    public Manager toManager() {
        Manager manager = new Manager();
        manager.setEmail(email);
        manager.setPassword(password);
        manager.setName(name);
        manager.setPosition(position);
        manager.setSalary(salary);
        return manager;
    }

}
