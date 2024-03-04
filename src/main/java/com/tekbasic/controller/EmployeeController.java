package com.tekbasic.controller;

import com.tekbasic.domain.Employee;
import com.tekbasic.dto.AbsenceForm;
import com.tekbasic.dto.response.MemberSessionResponse;
import com.tekbasic.service.EmployeeService;
import com.tekbasic.session.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    public String home() {
        return "employees/home";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberSessionResponse user = (MemberSessionResponse) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long id = user.getId();
        Employee employee = employeeService.findById(id).orElse(null);
        if (employee != null) {
            model.addAttribute("profile", employee);
            return "employees/profile";
        }
        return "redirect:/employees/home";
    }

    @GetMapping("/requests/add")
    public String requestLeaveAbsencePage(@ModelAttribute("absenceForm") AbsenceForm absenceForm, Model model) {
        List<String> managers = employeeService.findAllManager().stream().map(m -> m.getName()).collect(Collectors.toList());
        model.addAttribute("managers", managers);
        return "/employees/addRequest";
    }

    @PostMapping("/requests/add")
    public String addRequest(@ModelAttribute("absenceForm") AbsenceForm absenceForm) {


        return "redirect:/employees/home";
    }
}
