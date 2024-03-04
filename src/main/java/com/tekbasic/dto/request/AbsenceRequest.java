package com.tekbasic.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AbsenceRequest {

    private String employee;
    private String manager;
    private String reason;
    private String fromDate;
    private String toDate;

}
