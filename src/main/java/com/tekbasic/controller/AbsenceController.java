package com.tekbasic.controller;

import com.tekbasic.domain.Absence;
import com.tekbasic.domain.Employee;
import com.tekbasic.dto.AbsenceForm;
import com.tekbasic.dto.response.AbsenceResponse;
import com.tekbasic.dto.response.MemberSessionResponse;
import com.tekbasic.service.AbsenceService;
import com.tekbasic.session.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/absences")
public class AbsenceController {

    private final AbsenceService absenceService;

    @Autowired
    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }


    @PostMapping("/add")
    public String addRequest(@ModelAttribute("absenceForm") AbsenceForm absenceForm,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberSessionResponse user = (MemberSessionResponse) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long userId = user.getId();

        Absence absence = absenceForm.toAbsence();
        Employee employee = absenceService.findEmployeeByEmployeeId(userId).orElse(null);
        LocalDate fromDate = absence.getFromDate();
        LocalDate toDate = absence.getToDate();

        Period period = Period.between(fromDate, toDate);
        int days = period.getDays();

        if (employee.getCount() + days + 1 > 20) {
            return "redirect:/employees/requests/add";
        }
        absenceService.addRequest(absenceForm, userId);

        return "redirect:/employees/home";
    }

    @GetMapping
    public String viewRequest(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberSessionResponse user = (MemberSessionResponse) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long employeeId = user.getId();
        List<Absence> requests = absenceService.findByEmployeeId(employeeId);
        List<AbsenceResponse> absences = requests.stream().map(a -> a.toAbsenceResponse()).collect(Collectors.toList());
        model.addAttribute("absences", absences);
        return "/employees/requests";
    }

    @GetMapping("/managers")
    public String viewRequestManager(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberSessionResponse user = (MemberSessionResponse) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long managerId = user.getId();
        List<Absence> requests = absenceService.findByManagerId(managerId);
        List<AbsenceResponse> absences = requests.stream().map(a -> a.toAbsenceResponse()).collect(Collectors.toList());
        model.addAttribute("absences", absences);
        return "/managers/requests";
    }

    @GetMapping("/approve")
    public String absenceApprove(@RequestParam("id") Long absenceId) {
        absenceService.absenceApprove(absenceId);

        return "redirect:/absences/managers";
    }

    @GetMapping("/reject")
    public String absenceReject(@RequestParam("id") Long absenceId) {
        absenceService.absenceReject(absenceId);
        return "redirect:/absences/managers";
    }
}
