package com.tekbasic.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AbsenceResponse {

    private Long id;
    private String employee;
    private String manager;
    private String reason;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String process;


}
