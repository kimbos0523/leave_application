package com.tekbasic.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginForm {

    private String role;
    private String email;
    private String password;


}
