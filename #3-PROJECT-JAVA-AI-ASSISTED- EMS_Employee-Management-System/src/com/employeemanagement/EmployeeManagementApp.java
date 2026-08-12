package com.employeemanagement;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeManagementApp {
    private final EmployeeService service;
    private final Scanner scanner;

    public EmployeeManagementApp() {
        this.service = new EmployeeService(new EmployeeRepository());
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new EmployeeManagementApp().run();
    }

    private void run() {
        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> createEmployee();
                case 2 -> listAllEmployees();
                case 3 -> getEmployeeById();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> searchEmployeeByName();
                case 7 -> findEmployeesByDepartment();
                case 8 -> findEmployeesWithSalaryGreaterThan();
                case 9 -> {
                    System.out.println("Exiting EMS. Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("=== Employee Management System ===");
        System.out.println("1. Create employee");
        System.out.println("2. Get all employees");
        System.out.println("3. Get employee by ID");
        System.out.println("4. Update employee");
        System.out.println("5. Delete employee");
        System.out.println("6. Search employee by name");
        System.out.println("7. Find employee by department");
        System.out.println("8. Find employees with salary greater than amount");
        System.out.println("9. Exit");
    }

    private void createEmployee() {
        System.out.println("--- Create Employee ---");
        String name = readText("Name: ");
        String department = readText("Department: ");
        String position = readText("Position: ");
        double salary = readDouble("Salary: ");

        try {
            Employee employee = service.createEmployee(name, department, position, salary);
            System.out.println("Employee created: " + employee);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void listAllEmployees() {
        System.out.println("--- All Employees ---");
        List<Employee> employees = service.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        employees.forEach(System.out::println);
    }

    private void getEmployeeById() {
        System.out.println("--- Get Employee By ID ---");
        int id = readInt("Employee ID: ");
        Optional<Employee> employee = service.getEmployeeById(id);
        employee.ifPresentOrElse(
            System.out::println,
            () -> System.out.println("Employee not found with ID " + id)
        );
    }

    private void updateEmployee() {
        System.out.println("--- Update Employee ---");
        int id = readInt("Employee ID: ");
        String name = readText("New name (leave blank to keep current): ");
        String department = readText("New department (leave blank to keep current): ");
        String position = readText("New position (leave blank to keep current): ");
        double salary = readDoubleOrNegative("New salary (enter negative to keep current): ");

        try {
            Optional<Employee> updated = service.updateEmployee(id, name, department, position, salary);
            updated.ifPresentOrElse(
                emp -> System.out.println("Updated employee: " + emp),
                () -> System.out.println("Employee not found with ID " + id)
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void deleteEmployee() {
        System.out.println("--- Delete Employee ---");
        int id = readInt("Employee ID: ");
        boolean removed = service.deleteEmployee(id);
        System.out.println(removed ? "Employee deleted." : "Employee not found.");
    }

    private void searchEmployeeByName() {
        System.out.println("--- Search Employee By Name ---");
        String name = readText("Name search term: ");
        List<Employee> employees = service.searchByName(name);
        printSearchResults(employees);
    }

    private void findEmployeesByDepartment() {
        System.out.println("--- Find Employees By Department ---");
        String department = readText("Department: ");
        List<Employee> employees = service.findByDepartment(department);
        printSearchResults(employees);
    }

    private void findEmployeesWithSalaryGreaterThan() {
        System.out.println("--- Find Employees With Salary Greater Than ---");
        double salary = readDouble("Salary threshold: ");
        List<Employee> employees = service.findBySalaryGreaterThan(salary);
        printSearchResults(employees);
    }

    private void printSearchResults(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No matching employees found.");
            return;
        }
        employees.forEach(System.out::println);
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Salary cannot be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }

    private double readDoubleOrNegative(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return -1;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid decimal number or leave blank.");
            }
        }
    }
}
