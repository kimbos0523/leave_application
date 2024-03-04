package com.tekbasic.controller;

import com.tekbasic.domain.Manager;
import com.tekbasic.dto.response.MemberSessionResponse;
import com.tekbasic.service.ManagerService;
import com.tekbasic.session.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/home")
    public String home() {
        return "managers/home";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberSessionResponse user = (MemberSessionResponse) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long id = user.getId();
        Manager manager = managerService.findById(id).orElse(null);
        if (manager != null) {
            model.addAttribute("profile", manager);
            return "managers/profile";
        }
        return "redirect:/managers/home";
    }
}
