package com.employeemanagement;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee createEmployee(String name, String department, String position, double salary) {
        validateName(name);
        validateDepartment(department);
        validatePosition(position);
        validateSalary(salary);
        Employee employee = new Employee(0, name.trim(), department.trim(), position.trim(), salary);
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return repository.findById(id);
    }

    public Optional<Employee> updateEmployee(int id, String name, String department, String position, double salary) {
        Optional<Employee> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        Employee employee = existing.get();
        if (!name.isBlank()) {
            validateName(name);
            employee.setName(name.trim());
        }
        if (!department.isBlank()) {
            validateDepartment(department);
            employee.setDepartment(department.trim());
        }
        if (!position.isBlank()) {
            validatePosition(position);
            employee.setPosition(position.trim());
        }
        if (salary >= 0) {
            validateSalary(salary);
            employee.setSalary(salary);
        }
        repository.save(employee);
        return Optional.of(employee);
    }

    public boolean deleteEmployee(int id) {
        return repository.deleteById(id);
    }

    public List<Employee> searchByName(String name) {
        return repository.searchByName(name);
    }

    public List<Employee> findByDepartment(String department) {
        return repository.findByDepartment(department);
    }

    public List<Employee> findBySalaryGreaterThan(double salary) {
        return repository.findBySalaryGreaterThan(salary);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be empty.");
        }
    }

    private void validateDepartment(String department) {
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
    }

    private void validatePosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Position cannot be empty.");
        }
    }

    private void validateSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
    }
}
