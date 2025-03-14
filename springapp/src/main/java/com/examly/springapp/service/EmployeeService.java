package com.examly.springapp.service;

import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepo employeeRepo;

    @Autowired
    public void setEmployeeRepo(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepo.findById(id);
    }

    public Map<String, List<Employee>> groupByAttribute(String attribute) {
        List<Employee> employees = employeeRepo.findAll();
        switch (attribute) {
            case "designation":
                return employees.stream().collect(Collectors.groupingBy(Employee::getDesignation));
            case "name":
                return employees.stream().collect(Collectors.groupingBy(Employee::getName));
            default:
                return new HashMap<>();
        }
    }

    public List<Employee> findByAttribute(String attribute, String value) {
        List<Employee> employees = employeeRepo.findAll();
        switch (attribute) {
            case "designation":
                return employees.stream().filter(e -> e.getDesignation().equalsIgnoreCase(value))
                        .collect(Collectors.toList());
            case "name":
                return employees.stream().filter(e -> e.getName().equalsIgnoreCase(value)).collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }

    public List<Employee> getEmployeesBySalaryRange(double min, double max) {
        return employeeRepo.findAll().stream()
                .filter(e -> e.getSalary() >= min && e.getSalary() <= max)
                .collect(Collectors.toList());
    }
}




