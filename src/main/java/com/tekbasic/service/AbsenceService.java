package com.tekbasic.service;

import com.tekbasic.domain.Absence;
import com.tekbasic.domain.Employee;
import com.tekbasic.domain.Manager;
import com.tekbasic.dto.AbsenceForm;
import com.tekbasic.repository.AbsenceRepository;
import com.tekbasic.repository.EmployeeRepository;
import com.tekbasic.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;


    public void addRequest(AbsenceForm absenceForm, Long userId) {
        String managerName = absenceForm.getManager();
        Manager manager = managerRepository.findByName(managerName).orElse(null);
        Employee employee = employeeRepository.findById(userId).orElse(null);
        Absence absence = absenceForm.toAbsence();

        absence.setManager(manager);
        absence.setEmployee(employee);

        absenceRepository.save(absence);
    }

    public Optional<Employee> findEmployeeByEmployeeId(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Optional<Manager> findManagerByManagerId(Long managerId) {
        return managerRepository.findById(managerId);
    }

    public List<Absence> findAll() {
        return absenceRepository.findAll();
    }

    public List<Absence> findByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        return absenceRepository.findByEmployee(employee);
    }

    public List<Absence> findByManagerId(Long managerId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);
        return absenceRepository.findByManager(manager);
    }

    public Optional<Absence> findById(Long absenceId) {
        return absenceRepository.findById(absenceId);
    }

    public void absenceApprove(Long absenceId) {
        Absence absence = absenceRepository.findById(absenceId).orElse(null);
        absence.setProcess("Approval");

        Employee employee = absence.getEmployee();
        LocalDate fromDate = absence.getFromDate();
        LocalDate toDate = absence.getToDate();
        Period period = Period.between(fromDate, toDate);
        int days = period.getDays();
        employee.setCount(employee.getCount() + days + 1);
        absence.setEmployee(employee);
        absenceRepository.save(absence);
    }

    public void absenceReject(Long absenceId) {
        Absence absence = absenceRepository.findById(absenceId).orElse(null);
        absence.setProcess("Rejection");
        absenceRepository.save(absence);
    }

    public void save(Absence absence) {
        absenceRepository.save(absence);
    }

    public void update(Absence absence) {
        absenceRepository.save(absence);
    }

    public void deleteById(Long id) {
        absenceRepository.deleteById(id);
    }
}
