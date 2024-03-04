package com.tekbasic.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeResponse {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String position;
    private int salary;
    private int count;


}
