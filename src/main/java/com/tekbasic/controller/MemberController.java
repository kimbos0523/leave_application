package com.tekbasic.controller;

import com.tekbasic.dto.MemberForm;
import com.tekbasic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/add")
    public String addForm(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "members/addMember";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("memberForm") MemberForm memberForm) {
        memberService.saveMemberForm(memberForm);
        return "redirect:/";
    }
}
