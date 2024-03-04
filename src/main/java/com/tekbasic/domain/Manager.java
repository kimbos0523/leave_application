package com.tekbasic.domain;

import com.tekbasic.dto.response.ManagerResponse;
import com.tekbasic.dto.response.MemberSessionResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @Column(name = "manager_email")
    private String email;

    @Column(name = "manager_password")
    private String password;

    @Column(name = "manager_name")
    private String name;

    @Column(name = "manager_position")
    private String position;

    @Column(name = "manager_salary")
    private int salary;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Absence> absences = new ArrayList<>();


    public MemberSessionResponse sessionResponse() {
        MemberSessionResponse memberSessionResponse = new MemberSessionResponse();
        memberSessionResponse.setId(this.id);
        memberSessionResponse.setName(this.name);
        memberSessionResponse.setRole("manager");
        return memberSessionResponse;
    }

    public ManagerResponse managerResponse() {
        ManagerResponse managerResponse = new ManagerResponse();
        managerResponse.setId(this.id);
        managerResponse.setEmail(this.email);
        managerResponse.setPassword(this.password);
        managerResponse.setName(this.name);
        managerResponse.setPosition(this.position);
        managerResponse.setSalary(this.salary);
        return managerResponse;
    }
}
