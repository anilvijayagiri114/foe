# Employee Management System - API Documentation

Complete API reference for all classes and methods in the Employee Management System.

---

## Table of Contents

1. [Employee Class](#employee-class)
2. [EmployeeRepository Class](#employeerepository-class)
3. [EmployeeService Class](#employeeservice-class)
4. [EmployeeManagementApp Class](#employeemanagementapp-class)

---

## Employee Class

### Location
`src/main/java/com/employeemanagement/Employee.java`

### Description
Represents an employee entity with basic attributes. This is the data model class.

### Constructors

#### `Employee(int id, String name, String department, String position, double salary)`
Creates a new Employee object with the specified details.

**Parameters:**
- `id` (int) - Unique employee identifier
- `name` (String) - Employee's full name
- `department` (String) - Employee's department
- `position` (String) - Employee's job position
- `salary` (double) - Employee's annual salary

**Example:**
```java
Employee emp = new Employee(1001, "John Doe", "IT", "Engineer", 50000);
```

### Methods

#### `getId()`
Returns the employee's ID.

**Return Type:** `int`

**Example:**
```java
int id = emp.getId(); // Returns 1001
```

#### `getName()`
Returns the employee's name.

**Return Type:** `String`

**Example:**
```java
String name = emp.getName(); // Returns "John Doe"
```

#### `setName(String name)`
Updates the employee's name.

**Parameters:**
- `name` (String) - New name value

**Return Type:** `void`

**Example:**
```java
emp.setName("Jane Doe");
```

#### `getDepartment()`
Returns the employee's department.

**Return Type:** `String`

**Example:**
```java
String dept = emp.getDepartment(); // Returns "IT"
```

#### `setDepartment(String department)`
Updates the employee's department.

**Parameters:**
- `department` (String) - New department

**Return Type:** `void`

**Example:**
```java
emp.setDepartment("HR");
```

#### `getPosition()`
Returns the employee's position.

**Return Type:** `String`

**Example:**
```java
String pos = emp.getPosition(); // Returns "Engineer"
```

#### `setPosition(String position)`
Updates the employee's position.

**Parameters:**
- `position` (String) - New position

**Return Type:** `void`

**Example:**
```java
emp.setPosition("Senior Engineer");
```

#### `getSalary()`
Returns the employee's salary.

**Return Type:** `double`

**Example:**
```java
double salary = emp.getSalary(); // Returns 50000.0
```

#### `setSalary(double salary)`
Updates the employee's salary.

**Parameters:**
- `salary` (double) - New salary amount

**Return Type:** `void`

**Example:**
```java
emp.setSalary(55000);
```

#### `toString()`
Returns a string representation of the employee.

**Return Type:** `String`

**Example:**
```java
System.out.println(emp.toString());
// Output: Employee{id=1001, name='John Doe', department='IT', position='Engineer', salary=50000.0}
```

---

## EmployeeRepository Class

### Location
`src/main/java/com/employeemanagement/EmployeeRepository.java`

### Description
Handles all data access operations. Uses an in-memory HashMap to store employee records.

### Constructors

#### `EmployeeRepository()`
Creates a new repository with an empty HashMap for storing employees.

**Example:**
```java
EmployeeRepository repo = new EmployeeRepository();
```

### Methods

#### `save(Employee employee)`
Saves a new employee to the repository.

**Parameters:**
- `employee` (Employee) - Employee object to save

**Return Type:** `void`

**Throws:** None (but should validate before calling)

**Example:**
```java
Employee emp = new Employee(1001, "John", "IT", "Engineer", 50000);
repo.save(emp);
```

#### `findById(int id)`
Retrieves an employee by their ID.

**Parameters:**
- `id` (int) - Employee ID to search

**Return Type:** `Optional<Employee>` - Contains employee if found

**Example:**
```java
Optional<Employee> emp = repo.findById(1001);
if (emp.isPresent()) {
    System.out.println(emp.get().getName());
}
```

#### `findAll()`
Retrieves all employees in the repository.

**Return Type:** `List<Employee>` - List of all employees

**Example:**
```java
List<Employee> allEmployees = repo.findAll();
for (Employee emp : allEmployees) {
    System.out.println(emp.getName());
}
```

#### `update(Employee employee)`
Updates an existing employee record.

**Parameters:**
- `employee` (Employee) - Updated employee object

**Return Type:** `void`

**Example:**
```java
employee.setName("Jane Doe");
repo.update(employee);
```

#### `delete(int id)`
Deletes an employee by their ID.

**Parameters:**
- `id` (int) - Employee ID to delete

**Return Type:** `boolean` - true if deleted, false if not found

**Example:**
```java
boolean deleted = repo.delete(1001);
if (deleted) {
    System.out.println("Employee deleted");
}
```

#### `findByName(String name)`
Searches for employees by name (partial match).

**Parameters:**
- `name` (String) - Name to search (case-sensitive)

**Return Type:** `List<Employee>` - List of matching employees

**Example:**
```java
List<Employee> results = repo.findByName("John");
```

#### `findByDepartment(String department)`
Retrieves all employees in a specific department.

**Parameters:**
- `department` (String) - Department name

**Return Type:** `List<Employee>` - List of employees in department

**Example:**
```java
List<Employee> itEmployees = repo.findByDepartment("IT");
```

#### `findBySalaryGreaterThan(double salary)`
Retrieves employees with salary greater than or equal to the specified amount.

**Parameters:**
- `salary` (double) - Minimum salary threshold

**Return Type:** `List<Employee>` - Matching employees

**Example:**
```java
List<Employee> highSalary = repo.findBySalaryGreaterThan(50000);
```

#### `existsById(int id)`
Checks if an employee exists.

**Parameters:**
- `id` (int) - Employee ID to check

**Return Type:** `boolean` - true if exists

**Example:**
```java
if (repo.existsById(1001)) {
    System.out.println("Employee exists");
}
```

#### `count()`
Returns the total number of employees.

**Return Type:** `int` - Number of employees

**Example:**
```java
int total = repo.count();
```

---

## EmployeeService Class

### Location
`src/main/java/com/employeemanagement/EmployeeService.java`

### Description
Contains business logic and validation for employee operations.

### Constructors

#### `EmployeeService(EmployeeRepository repository)`
Creates a new service with a repository dependency.

**Parameters:**
- `repository` (EmployeeRepository) - Repository instance

**Example:**
```java
EmployeeRepository repo = new EmployeeRepository();
EmployeeService service = new EmployeeService(repo);
```

### Methods

#### `createEmployee(Employee employee)`
Creates a new employee with validation.

**Parameters:**
- `employee` (Employee) - Employee to create

**Return Type:** `void`

**Throws:** `IllegalArgumentException` if validation fails

**Validation Rules:**
- Name cannot be empty
- Salary must be > 0
- Department cannot be empty
- ID must not already exist

**Example:**
```java
Employee emp = new Employee(1001, "John", "IT", "Engineer", 50000);
service.createEmployee(emp);
```

#### `getEmployeeById(int id)`
Retrieves an employee by ID.

**Parameters:**
- `id` (int) - Employee ID

**Return Type:** `Optional<Employee>`

**Example:**
```java
Optional<Employee> emp = service.getEmployeeById(1001);
```

#### `getAllEmployees()`
Retrieves all employees.

**Return Type:** `List<Employee>`

**Example:**
```java
List<Employee> all = service.getAllEmployees();
```

#### `updateEmployee(Employee employee)`
Updates an existing employee with validation.

**Parameters:**
- `employee` (Employee) - Updated employee

**Return Type:** `void`

**Throws:** `IllegalArgumentException` if employee doesn't exist or validation fails

**Example:**
```java
employee.setName("Jane");
service.updateEmployee(employee);
```

#### `deleteEmployee(int id)`
Deletes an employee.

**Parameters:**
- `id` (int) - Employee ID

**Return Type:** `boolean` - true if deleted

**Example:**
```java
boolean deleted = service.deleteEmployee(1001);
```

#### `searchEmployeeByName(String name)`
Searches for employees by name.

**Parameters:**
- `name` (String) - Search name

**Return Type:** `List<Employee>`

**Example:**
```java
List<Employee> results = service.searchEmployeeByName("John");
```

#### `findEmployeesByDepartment(String department)`
Finds all employees in a department.

**Parameters:**
- `department` (String) - Department name

**Return Type:** `List<Employee>`

**Example:**
```java
List<Employee> itTeam = service.findEmployeesByDepartment("IT");
```

#### `findEmployeesWithSalaryGreaterThan(double salary)`
Finds employees with salary above a threshold.

**Parameters:**
- `salary` (double) - Minimum salary

**Return Type:** `List<Employee>`

**Example:**
```java
List<Employee> highPaid = service.findEmployeesWithSalaryGreaterThan(50000);
```

---

## EmployeeManagementApp Class

### Location
`src/main/java/com/employeemanagement/EmployeeManagementApp.java`

### Description
Main application class with console-based user interface.

### Constructors

#### `EmployeeManagementApp()`
Initializes the application with repository and scanner.

**Example:**
```java
EmployeeManagementApp app = new EmployeeManagementApp();
```

### Methods

#### `main(String[] args)`
Entry point of the application.

**Parameters:**
- `args` (String[]) - Command line arguments (not used)

**Return Type:** `void`

**Example:**
```bash
java com.employeemanagement.EmployeeManagementApp
```

#### `run()`
Main application loop displaying menu and processing user input.

**Return Type:** `void`

**Example:**
```java
app.run();
```

#### `printMenu()`
Displays the main menu options.

**Return Type:** `void`

Output:
```
=== Employee Management System ===
1. Create employee
2. Get all employees
...
```

#### `createEmployee()`
Handles employee creation through user input.

**Return Type:** `void`

**User Prompts:**
- Employee ID
- Name
- Department
- Position
- Salary

#### `listAllEmployees()`
Displays all employees.

**Return Type:** `void`

#### `getEmployeeById()`
Retrieves and displays a specific employee.

**Return Type:** `void`

**User Prompts:**
- Employee ID

#### `updateEmployee()`
Updates an existing employee.

**Return Type:** `void`

**User Prompts:**
- Employee ID
- Field to update
- New value

#### `deleteEmployee()`
Deletes an employee.

**Return Type:** `void`

**User Prompts:**
- Employee ID

#### `searchEmployeeByName()`
Searches for employees by name.

**Return Type:** `void`

**User Prompts:**
- Name to search

#### `findEmployeesByDepartment()`
Displays employees in a specific department.

**Return Type:** `void`

**User Prompts:**
- Department name

#### `findEmployeesWithSalaryGreaterThan()`
Displays high-earning employees.

**Return Type:** `void`

**User Prompts:**
- Minimum salary

#### `readInt(String prompt)`
Reads an integer from user input.

**Parameters:**
- `prompt` (String) - Message to display

**Return Type:** `int`

**Example:**
```java
int id = readInt("Enter ID: ");
```

#### `readString(String prompt)`
Reads a string from user input.

**Parameters:**
- `prompt` (String) - Message to display

**Return Type:** `String`

**Example:**
```java
String name = readString("Enter name: ");
```

---

## Usage Examples

### Complete Example: Create and Display Employee

```java
// Create repository and service
EmployeeRepository repo = new EmployeeRepository();
EmployeeService service = new EmployeeService(repo);

// Create employee
Employee emp = new Employee(1001, "John Doe", "IT", "Engineer", 50000);
service.createEmployee(emp);

// Retrieve and display
Optional<Employee> retrieved = service.getEmployeeById(1001);
if (retrieved.isPresent()) {
    System.out.println(retrieved.get().toString());
}

// Update
emp.setSalary(55000);
service.updateEmployee(emp);

// Search
List<Employee> results = service.searchEmployeeByName("John");
results.forEach(e -> System.out.println(e));

// Delete
service.deleteEmployee(1001);
```

### Complete Example: Search by Department

```java
EmployeeRepository repo = new EmployeeRepository();
EmployeeService service = new EmployeeService(repo);

// Add employees
service.createEmployee(new Employee(1, "Alice", "IT", "Developer", 60000));
service.createEmployee(new Employee(2, "Bob", "HR", "Manager", 55000));
service.createEmployee(new Employee(3, "Charlie", "IT", "Tester", 50000));

// Find IT employees
List<Employee> itTeam = service.findEmployeesByDepartment("IT");
System.out.println("IT Team size: " + itTeam.size()); // Output: 2
```

---

## Exception Handling

### IllegalArgumentException
Thrown when:
- Creating employee with empty name
- Creating employee with invalid salary (≤ 0)
- Creating employee with empty department
- Updating non-existent employee
- Creating employee with duplicate ID

**How to handle:**
```java
try {
    service.createEmployee(emp);
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}
```

---

## Best Practices

1. **Always check Optional** before calling `.get()`
   ```java
   Optional<Employee> emp = service.getEmployeeById(1001);
   if (emp.isPresent()) {
       // Safe to use emp.get()
   }
   ```

2. **Validate input before creating**
   ```java
   if (name.isEmpty() || salary <= 0) {
       System.out.println("Invalid input");
       return;
   }
   ```

3. **Handle exceptions gracefully**
   ```java
   try {
       service.createEmployee(emp);
   } catch (IllegalArgumentException e) {
       System.out.println("Creation failed: " + e.getMessage());
   }
   ```

4. **Iterate safely over lists**
   ```java
   List<Employee> employees = service.getAllEmployees();
   if (employees.isEmpty()) {
       System.out.println("No employees found");
   } else {
       employees.forEach(System.out::println);
   }
   ```

---

## Performance Considerations

- **findById(id)**: O(1) - Direct HashMap lookup
- **findByName(name)**: O(n) - Must search all records
- **findByDepartment(dept)**: O(n) - Must search all records
- **save()**: O(1) - Direct HashMap insertion
- **delete()**: O(1) - Direct HashMap removal
- **getAllEmployees()**: O(n) - Returns all records

---

## Version Information

- **API Version**: 1.0.0
- **Last Updated**: 2024
- **Minimum Java Version**: 11
- **Status**: Stable

---

## See Also

- [ARCHITECTURE.md](ARCHITECTURE.md) - System design and patterns
- [BEGINNER_GUIDE.md](BEGINNER_GUIDE.md) - Learning resources
- [README.md](../README.md) - Project overview
