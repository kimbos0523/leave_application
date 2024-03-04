package com.tekbasic.dto.request;

import com.tekbasic.domain.Employee;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeRequest {

    private String email;
    private String password;
    private String name;
    private String position;
    private int salary;
    private int count;


    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setEmail(this.email);
        employee.setPassword(this.password);
        employee.setName(this.name);
        employee.setPosition(this.position);
        employee.setSalary(this.salary);
        employee.setCount(this.count);
        return employee;
    }
}
