package com.tekbasic.repository;

import com.tekbasic.domain.Absence;
import com.tekbasic.domain.Employee;
import com.tekbasic.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    List<Absence> findByEmployee(Employee employee);

    List<Absence> findByManager(Manager manager);
}
