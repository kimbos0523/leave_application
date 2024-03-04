package com.tekbasic.controller;

import com.tekbasic.domain.Employee;
import com.tekbasic.domain.Manager;
import com.tekbasic.dto.LoginForm;
import com.tekbasic.dto.response.MemberSessionResponse;
import com.tekbasic.service.LoginService;
import com.tekbasic.session.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request) {
        String role = loginForm.getRole();
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        if (role.equals("employee")) {
            Employee employee = loginService.loginEmployee(email, password);
            if (employee == null) {
                System.out.println("null??");
                return "redirect:/login";
            } else {
                MemberSessionResponse memberSessionResponse = employee.sessionResponse();
                HttpSession session = request.getSession();
                System.out.println("Iamhere");
                session.setAttribute(SessionConst.LOGIN_MEMBER, memberSessionResponse);
                return "employees/home";
            }
        } else if (role.equals("manager")) {
            Manager manager = loginService.loginManager(email, password);
            if (manager == null) {
                return "redirect:/login";
            } else {
                MemberSessionResponse memberSessionResponse = manager.sessionResponse();
                HttpSession session = request.getSession();
                session.setAttribute(SessionConst.LOGIN_MEMBER, memberSessionResponse);
                return "managers/home";
            }
        } else {
            throw new IllegalArgumentException("Role should be either employee or manager");
        }
    }

}
