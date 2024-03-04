package com.tekbasic.repository;

import com.tekbasic.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByName(String name);

    Optional<Manager> findByEmailAndPassword(String email, String password);

}
