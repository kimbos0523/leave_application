package com.tekbasic.dto.request;

import com.tekbasic.domain.Manager;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ManagerRequest {

    private String email;
    private String password;
    private String name;
    private String position;
    private int salary;


    public Manager toManager() {
        Manager manager = new Manager();
        manager.setEmail(this.email);
        manager.setPassword(this.password);
        manager.setName(this.name);
        manager.setPosition(this.position);
        manager.setSalary(this.salary);
        return manager;
    }
}
