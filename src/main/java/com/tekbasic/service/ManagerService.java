package com.tekbasic.service;

import com.tekbasic.domain.Manager;
import com.tekbasic.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerService {

    private final ManagerRepository managerRepository;


    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    public void save(Manager manager) {
        managerRepository.save(manager);
    }

    public void update(Manager manager) {
        managerRepository.save(manager);
    }

    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    public Optional<Manager> findByName(String managerName) {
        return managerRepository.findByName(managerName);
    }
}
