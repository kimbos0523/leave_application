package com.tekbasic.domain;

import com.tekbasic.dto.response.AbsenceResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "absence")
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absence_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Column(name = "reason")
    private String reason;

    @Column(name = "fromDate")
    private LocalDate fromDate;

    @Column(name = "toDate")
    private LocalDate toDate;

    @Column(name = "process")
    private String process;


    public AbsenceResponse toAbsenceResponse() {
        AbsenceResponse absenceResponse = new AbsenceResponse();
        absenceResponse.setId(this.id);
        absenceResponse.setEmployee(this.employee.getName());
        absenceResponse.setManager(this.manager.getName());
        absenceResponse.setReason(this.reason);
        absenceResponse.setFromDate(this.fromDate);
        absenceResponse.setToDate(this.toDate);
        absenceResponse.setProcess(this.process);
        return absenceResponse;
    }
}
