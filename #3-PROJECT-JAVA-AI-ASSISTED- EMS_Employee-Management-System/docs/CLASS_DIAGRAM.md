# Employee Management System - Class Diagram & Architecture

## 📐 System Architecture Overview

```
┌─────────────────────────────────────────────────────────────────┐
│                    EmployeeManagementApp                         │
│              (User Interface / Menu Controller)                  │
└──────────────────────────┬──────────────────────────────────────┘
                           │
                           │ uses
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│                   EmployeeService                                │
│          (Business Logic / Service Layer)                        │
│  - createEmployee()                                              │
│  - updateEmployee()                                              │
│  - deleteEmployee()                                              │
│  - searchByName()                                                │
│  - getByDepartment()                                             │
└──────────────────────────┬──────────────────────────────────────┘
                           │
                           │ uses
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│               EmployeeRepository                                 │
│          (Data Access / Repository Layer)                        │
│  - save()                                                        │
│  - findById()                                                    │
│  - update()                                                      │
│  - delete()                                                      │
│  - findAll()                                                     │
└──────────────────────────┬──────────────────────────────────────┘
                           │
                           │ manages
                           ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Employee                                      │
│              (Data Model / Entity)                               │
│  - id: int                                                       │
│  - name: String                                                  │
│  - department: String                                            │
│  - position: String                                              │
│  - salary: double                                                │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🏗️ Detailed Class Structure

### 1. Employee Class (Model/Entity)

```
┌──────────────────────────────────┐
│         Employee                 │
├──────────────────────────────────┤
│ - id: int                        │
│ - name: String                   │
│ - department: String             │
│ - position: String               │
│ - salary: double                 │
├──────────────────────────────────┤
│ + Employee(...)                  │
│ + getId(): int                   │
│ + getName(): String              │
│ + setName(String): void          │
│ + getDepartment(): String        │
│ + setDepartment(String): void    │
│ + getPosition(): String          │
│ + setPosition(String): void      │
│ + getSalary(): double            │
│ + setSalary(double): void        │
│ + toString(): String             │
└──────────────────────────────────┘
```

**Purpose**: Represents an employee entity with essential attributes.

**Key Concepts**:
- Encapsulation: Private fields with public accessors
- Immutable ID: Cannot be changed after creation
- Mutable attributes: Can be modified after creation

---

### 2. EmployeeRepository Class (Data Access Layer)

```
┌───────────────────────────────────────┐
│    EmployeeRepository                 │
├───────────────────────────────────────┤
│ - employees: HashMap<Integer, Employee>
├───────────────────────────────────────┤
│ + save(Employee): void                │
│ + findById(int): Optional<Employee>   │
│ + findAll(): List<Employee>           │
│ + update(Employee): void              │
│ + delete(int): boolean                │
│ + findByName(String): List<Employee>  │
│ + findByDepartment(String): List<...> │
│ + findBySalaryGreaterThan(double)...  │
│ + existsById(int): boolean            │
│ + count(): int                        │
└───────────────────────────────────────┘
```

**Purpose**: Handles all data persistence operations.

**Key Concepts**:
- Repository Pattern: Abstracts data access logic
- Collections: Uses HashMap for efficient lookup
- CRUD Operations: Create, Read, Update, Delete
- Optional: Safer handling of null values

**Data Storage**:
```
HashMap<Integer, Employee>
    │
    ├─ 1001 → Employee(John Doe, IT, Engineer, 50000)
    ├─ 1002 → Employee(Jane Smith, HR, Manager, 60000)
    ├─ 1003 → Employee(Bob Johnson, Sales, Executive, 55000)
    └─ ...
```

---

### 3. EmployeeService Class (Business Logic Layer)

```
┌───────────────────────────────────────────────┐
│         EmployeeService                       │
├───────────────────────────────────────────────┤
│ - repository: EmployeeRepository              │
├───────────────────────────────────────────────┤
│ + createEmployee(Employee): void              │
│ + updateEmployee(Employee): void              │
│ + deleteEmployee(int): boolean                │
│ + getEmployeeById(int): Optional<Employee>    │
│ + getAllEmployees(): List<Employee>           │
│ + searchEmployeeByName(String): List<...>    │
│ + findEmployeesByDepartment(String): List<...>
│ + findBySalaryGreaterThan(double): List<...> │
│ + calculateAnnualBonus(int): double           │
│ + validateEmployee(Employee): boolean         │
└───────────────────────────────────────────────┘
```

**Purpose**: Contains all business logic and validation.

**Key Concepts**:
- Service Pattern: Encapsulates business operations
- Dependency Injection: Receives repository in constructor
- Validation: Ensures data integrity
- Separation of Concerns: Isolates business logic from data access

**Example Flow**:
```
Service receives create request
    ↓
Validates employee data
    ↓
Checks for duplicate ID
    ↓
Calls repository.save()
    ↓
Returns success/failure
```

---

### 4. EmployeeManagementApp Class (Presentation Layer)

```
┌──────────────────────────────────────────────┐
│   EmployeeManagementApp                      │
├──────────────────────────────────────────────┤
│ - service: EmployeeService                   │
│ - scanner: Scanner                           │
├──────────────────────────────────────────────┤
│ + main(String[]): void                       │
│ + run(): void                                │
│ + createEmployee(): void                     │
│ + listAllEmployees(): void                   │
│ + getEmployeeById(): void                    │
│ + updateEmployee(): void                     │
│ + deleteEmployee(): void                     │
│ + searchEmployeeByName(): void               │
│ + findEmployeesByDepartment(): void          │
│ + findEmployeesWithSalaryGreaterThan(): void │
│ + printMenu(): void                          │
│ + readInt(String): int                       │
│ + readString(String): String                 │
└──────────────────────────────────────────────┘
```

**Purpose**: Handles user interaction and menu.

**Key Concepts**:
- MVC Pattern: View/Controller layer
- User Input: Reads and validates user input
- Menu-Driven Interface: Simple console navigation
- Error Handling: Graceful error recovery

---

## 🔄 Data Flow Diagrams

### Creating an Employee

```
User Input
    │
    ↓
EmployeeManagementApp.createEmployee()
    │
    ├─ readInt(), readString() - Get user input
    │
    ↓
Create Employee object
    │
    ↓
EmployeeService.createEmployee()
    │
    ├─ validateEmployee() - Validate data
    │
    ↓
EmployeeRepository.save()
    │
    ├─ Store in HashMap
    │
    ↓
Return success message
```

### Searching for an Employee

```
User Input (search criteria)
    │
    ↓
EmployeeManagementApp.searchEmployeeByName()
    │
    ↓
EmployeeService.searchEmployeeByName()
    │
    ├─ Call repository method
    │
    ↓
EmployeeRepository.findByName()
    │
    ├─ Search in HashMap using stream/filter
    │
    ↓
Return matching employees
    │
    ↓
Display results to user
```

### Updating an Employee

```
User Input (ID and new data)
    │
    ↓
EmployeeManagementApp.updateEmployee()
    │
    ↓
EmployeeService.updateEmployee()
    │
    ├─ validateEmployee()
    │
    ├─ checkIfExists()
    │
    ↓
EmployeeRepository.update()
    │
    ├─ Replace in HashMap
    │
    ↓
Return success message
```

---

## 📊 Interaction Sequences

### Sequence: Create Employee

```
┌─────────┐      ┌──────────┐      ┌──────────┐      ┌────────────┐
│  User   │      │   App    │      │ Service  │      │ Repository │
└────┬────┘      └─────┬────┘      └─────┬────┘      └─────┬──────┘
     │                 │                  │                 │
     │─ Enter data ───>│                  │                 │
     │                 │                  │                 │
     │                 │─ createEmployee()→                │
     │                 │                  │                 │
     │                 │─ validateEmployee()               │
     │                 │ <─ valid ────────│                 │
     │                 │                  │                 │
     │                 │─────────── save(employee) ───────>│
     │                 │                  │                 │
     │                 │                  │  <─ success ────│
     │                 │ <─────── success ────────────────│
     │                 │                  │                 │
     │ <─ Success msg ─│                  │                 │
     │                 │                  │                 │
```

---

## 🎯 Design Patterns Used

### 1. **Repository Pattern**
- **What**: Abstracts data access logic
- **Where**: EmployeeRepository
- **Why**: Easier to test, switch data sources

### 2. **Service Pattern**
- **What**: Encapsulates business logic
- **Where**: EmployeeService
- **Why**: Separation of concerns, reusability

### 3. **Singleton Pattern** (Implicit)
- **What**: Single instance of repository and service
- **Where**: Managed in App class
- **Why**: Consistent data state

### 4. **MVC Pattern**
- **What**: Model-View-Controller separation
- **Where**: Employee (M), App (V+C), Service (Logic)
- **Why**: Clean architecture, maintainability

---

## 🧪 Testing Strategy

### Unit Test Classes

```
├── EmployeeTest
│   ├─ testEmployeeCreation()
│   ├─ testGettersSetters()
│   └─ testToString()
│
├── EmployeeRepositoryTest
│   ├─ testSave()
│   ├─ testFindById()
│   ├─ testUpdate()
│   ├─ testDelete()
│   └─ testFindByName()
│
└── EmployeeServiceTest
    ├─ testCreateValidEmployee()
    ├─ testCreateInvalidEmployee()
    ├─ testGetAllEmployees()
    ├─ testSearchByName()
    └─ testCalculateBonus()
```

---

## 🔄 Extension Points

### Adding New Features

**Example: Add Department Class**
```
New Class:
Department
├─ id: int
├─ name: String
├─ budget: double
└─ manager: Employee

New Repository:
DepartmentRepository

New Service:
DepartmentService

Update:
EmployeeService to include department management
```

**Example: Add Salary Management**
```
New Class:
SalarySlip
├─ employeeId: int
├─ date: LocalDate
├─ basicSalary: double
├─ bonus: double
└─ deductions: double

New Service:
SalaryService
├─ calculateSalary()
├─ generateSalarySlip()
└─ exportPayroll()
```

---

## 🎓 Key Learning Points

1. **Encapsulation**: Private fields with public accessors
2. **Separation of Concerns**: Different layers handle different responsibilities
3. **Collections**: HashMap for efficient data storage and retrieval
4. **Design Patterns**: Repository, Service, MVC
5. **Exception Handling**: Proper error handling and recovery
6. **Testing**: Unit tests for each component
7. **API Design**: Clear, intuitive interfaces

---

## 📚 References

- **OOP Concepts**: https://docs.oracle.com/javase/tutorial/java/concepts/
- **Collections**: https://docs.oracle.com/javase/tutorial/collections/
- **Design Patterns**: https://refactoring.guru/design-patterns/java

---

