package com.employeemanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepository {
    private final Map<Integer, Employee> storage = new HashMap<>();
    private int nextId = 1;

    public Employee save(Employee employee) {
        if (employee.getId() <= 0) {
            employee = new Employee(nextId++, employee.getName(), employee.getDepartment(), employee.getPosition(), employee.getSalary());
        }
        storage.put(employee.getId(), employee);
        return employee;
    }

    public List<Employee> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public boolean deleteById(int id) {
        return storage.remove(id) != null;
    }

    public List<Employee> searchByName(String name) {
        String query = name.trim().toLowerCase();
        List<Employee> result = new ArrayList<>();
        for (Employee employee : storage.values()) {
            if (employee.getName().toLowerCase().contains(query)) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> findByDepartment(String department) {
        String query = department.trim().toLowerCase();
        List<Employee> result = new ArrayList<>();
        for (Employee employee : storage.values()) {
            if (employee.getDepartment().toLowerCase().equals(query)) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> findBySalaryGreaterThan(double salary) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : storage.values()) {
            if (employee.getSalary() > salary) {
                result.add(employee);
            }
        }
        return result;
    }
}
