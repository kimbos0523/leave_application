package com.tekbasic.domain;

import com.tekbasic.dto.response.EmployeeResponse;
import com.tekbasic.dto.response.MemberSessionResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_email")
    private String email;

    @Column(name = "employee_password")
    private String password;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_position")
    private String position;

    @Column(name = "employee_salary")
    private int salary;

    @Column(name = "absence_count")
    private int count = 0;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Absence> absences = new ArrayList<>();


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", count=" + count +
                '}';
    }

    public MemberSessionResponse sessionResponse() {
        MemberSessionResponse memberSessionResponse = new MemberSessionResponse();
        memberSessionResponse.setId(this.id);
        memberSessionResponse.setName(this.name);
        memberSessionResponse.setRole("employee");
        return memberSessionResponse;
    }

    public EmployeeResponse employeeResponse() {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(this.id);
        employeeResponse.setEmail(this.email);
        employeeResponse.setPassword(this.password);
        employeeResponse.setName(this.name);
        employeeResponse.setPosition(this.position);
        employeeResponse.setSalary(this.salary);
        employeeResponse.setCount(this.count);
        return employeeResponse;
    }
}
