package com.tekbasic.controller;

import com.tekbasic.domain.Absence;
import com.tekbasic.domain.Employee;
import com.tekbasic.domain.Manager;
import com.tekbasic.dto.request.AbsenceRequest;
import com.tekbasic.dto.request.EmployeeRequest;
import com.tekbasic.dto.request.ManagerRequest;
import com.tekbasic.dto.response.AbsenceResponse;
import com.tekbasic.dto.response.EmployeeResponse;
import com.tekbasic.dto.response.ManagerResponse;
import com.tekbasic.service.AbsenceService;
import com.tekbasic.service.EmployeeService;
import com.tekbasic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LeaveRestController {

    private final AbsenceService absenceService;
    private final EmployeeService employeeService;
    private final ManagerService managerService;

    @Autowired
    public LeaveRestController(AbsenceService absenceService, EmployeeService employeeService, ManagerService managerService) {
        this.absenceService = absenceService;
        this.employeeService = employeeService;
        this.managerService = managerService;
    }


    @GetMapping("/absences")
    public List<AbsenceResponse> findAllAbsence() {
        return absenceService.findAll().stream().map(a -> a.toAbsenceResponse()).collect(Collectors.toList());
    }

    @PostMapping("/absences")
    public String saveAbsence(@RequestBody AbsenceRequest absenceRequest) {
        Absence absence = getAbsence(absenceRequest);
        absenceService.save(absence);
        return "Save Successful";
    }

    @PutMapping("/absences/{id}")
    public String updateAbsence(@PathVariable("id") Long id,
                                 @RequestBody AbsenceRequest absenceRequest) {
        Absence absence = getAbsence(absenceRequest);
        absence.setId(id);
        absenceService.update(absence);
        return "Update Successful";
    }

    @DeleteMapping("/absences/{id}")
    public String deleteAbsence(@PathVariable("id") Long id) {
        absenceService.deleteById(id);
        return "Delete Successful";
    }



    @GetMapping("/employees")
    public List<EmployeeResponse> findAllEmployee() {
        return employeeService.findAll().stream().map(e -> e.employeeResponse()).collect(Collectors.toList());
    }

    @PostMapping("/employees")
    public String saveEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRequest.toEmployee();
        employeeService.save(employee);
        return "Save Successful";
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(@PathVariable("id") Long id,
                                 @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeRequest.toEmployee();
        employee.setId(id);
        employeeService.update(employee);
        return "Update Successful";
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return "Delete Successful";
    }


    @GetMapping("/managers")
    public List<ManagerResponse> findAllManager() {
        return managerService.findAll().stream().map(m -> m.managerResponse()).collect(Collectors.toList());
    }

    @PostMapping("/managers")
    public String saveManager(@RequestBody ManagerRequest managerRequest) {
        Manager manager = managerRequest.toManager();
        managerService.save(manager);
        return "Save Successful";
    }

    @PutMapping("/managers/{id}")
    public String updateManager(@PathVariable("id") Long id,
                                 @RequestBody ManagerRequest managerRequest) {
        Manager manager = managerRequest.toManager();
        manager.setId(id);
        managerService.update(manager);
        return "Update Successful";
    }

    @DeleteMapping("/managers/{id}")
    public String deleteManager(@PathVariable("id") Long id) {
        managerService.deleteById(id);
        return "Delete Successful";
    }


    private Absence getAbsence(AbsenceRequest absenceRequest) {
        String employeeName = absenceRequest.getEmployee();
        String managerName = absenceRequest.getManager();

        Employee employee = employeeService.findByName(employeeName).orElse(null);
        Manager manager = managerService.findByName(managerName).orElse(null);
        Absence absence = new Absence();
        absence.setEmployee(employee);
        absence.setManager(manager);
        absence.setProcess(absenceRequest.getReason());
        absence.setFromDate(LocalDate.parse(absenceRequest.getFromDate()));
        absence.setToDate(LocalDate.parse(absenceRequest.getToDate()));
        return absence;
    }
}
