package com.examly.springapp.controller;

import com.examly.springapp.model.Employee;
import com.examly.springapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            return ResponseEntity.status(201).body(employeeService.addEmployee(employee));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            return ResponseEntity.ok(employeeService.getAllEmployees());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(500).build());
    }

    @GetMapping("/employees/groupBy/{attribute}")
    public ResponseEntity<Map<String, List<Employee>>> groupByAttribute(@PathVariable String attribute) {
        try {
            return ResponseEntity.ok(employeeService.groupByAttribute(attribute));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/employees/findBy/{attribute}")
    public ResponseEntity<List<Employee>> findByAttribute(@PathVariable String attribute, @RequestParam String value) {
        try {
            return ResponseEntity.ok(employeeService.findByAttribute(attribute, value));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/employees/salaryRange")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(@RequestParam double minSalary,
            @RequestParam double maxSalary) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeesBySalaryRange(minSalary, maxSalary));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
