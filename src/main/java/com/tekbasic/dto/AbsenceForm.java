package com.tekbasic.dto;

import com.tekbasic.domain.Absence;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AbsenceForm {

    private String manager;
    private String reason;
    private String fromDate;
    private String toDate;


    public Absence toAbsence() {
        Absence absence = new Absence();
        absence.setReason(this.reason);
        absence.setFromDate(LocalDate.parse(this.fromDate));
        absence.setToDate(LocalDate.parse(this.toDate));
        absence.setProcess("Awaiting Response...");
        return absence;
    }
}
