# Troubleshooting Guide - Employee Management System

Solutions to common issues when setting up and running the project.

---

## Common Issues and Solutions

### Issue 1: Java Command Not Found

**Error Message:**
```
'java' is not recognized as an internal or external command
```

**Causes:**
- Java is not installed
- Java is not in system PATH
- Wrong Java version

**Solutions:**

**Windows:**
1. Check if Java is installed:
   ```bash
   where java
   ```
   
2. If not found, download and install from:
   https://www.oracle.com/java/technologies/downloads/

3. Add Java to PATH:
   - Right-click "This PC" → Properties
   - Click "Advanced system settings"
   - Click "Environment Variables"
   - Under System variables, click "New"
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-11.0.x` (your path)
   - Click OK
   - Edit PATH variable
   - Add: `%JAVA_HOME%\bin`
   - Restart Command Prompt
   - Test: `java -version`

**macOS:**
```bash
# Install Java
brew install openjdk

# Set JAVA_HOME
echo 'export JAVA_HOME=$(/usr/libexec/java_home)' >> ~/.bash_profile
source ~/.bash_profile

# Test
java -version
```

**Linux:**
```bash
# Install Java
sudo apt update
sudo apt install openjdk-11-jdk

# Test
java -version
```

---

### Issue 2: Maven Command Not Found

**Error Message:**
```
'mvn' is not recognized as an internal or external command
```

**Solutions:**

**Windows:**
1. Download Maven from: https://maven.apache.org/download.cgi

2. Extract to a folder (e.g., `C:\tools\apache-maven-3.8.1`)

3. Add to PATH:
   - Environment Variables → New
   - Variable name: `M2_HOME`
   - Variable value: `C:\tools\apache-maven-3.8.1`
   - Edit PATH variable
   - Add: `%M2_HOME%\bin`
   - Restart Command Prompt
   - Test: `mvn --version`

**macOS:**
```bash
brew install maven
mvn --version
```

**Linux:**
```bash
sudo apt install maven
mvn --version
```

---

### Issue 3: Java Version Too Old

**Error Message:**
```
error: Source option 11 is not supported. Use 6 or 7.
```

**Cause:** You have Java 8 or older, but the project requires Java 11+

**Solution:**

1. Check current version:
   ```bash
   java -version
   ```

2. Download Java 11 or higher from:
   https://www.oracle.com/java/technologies/downloads/

3. Install the new version

4. Update JAVA_HOME to new location:
   ```bash
   # Windows: Set JAVA_HOME in Environment Variables
   # macOS/Linux: 
   echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 11)' >> ~/.bash_profile
   ```

5. Verify:
   ```bash
   java -version  # Should show Java 11 or higher
   ```

---

### Issue 4: Cannot Find Symbol

**Error Message:**
```
error: cannot find symbol
symbol: class EmployeeManagementApp
```

**Causes:**
- Source files are not in correct location
- Package name doesn't match directory structure
- File not compiled before running

**Solution:**

1. Verify directory structure:
   ```
   src/main/java/com/employeemanagement/
   ├── Employee.java
   ├── EmployeeRepository.java
   ├── EmployeeService.java
   └── EmployeeManagementApp.java
   ```

2. Verify package declaration in each file:
   ```java
   package com.employeemanagement;  // Must match directory path
   ```

3. Clean and recompile:
   ```bash
   mvn clean
   mvn compile
   ```

---

### Issue 5: NullPointerException

**Error Message:**
```
Exception in thread "main" java.lang.NullPointerException
    at com.employeemanagement.EmployeeService.createEmployee(EmployeeService.java:15)
```

**Causes:**
- Using an object without initializing it
- Trying to access Optional without checking if value exists
- Repository returns null without wrapping in Optional

**Example Problem:**
```java
Employee emp = null;  // Not initialized!
emp.getName();  // NullPointerException!
```

**Solutions:**

1. Always initialize objects:
   ```java
   Employee emp = new Employee(1, "John", "IT", "Engineer", 50000);
   ```

2. Check Optional safely:
   ```java
   Optional<Employee> emp = repository.findById(1);
   if (emp.isPresent()) {
       System.out.println(emp.get().getName());
   }
   ```

3. Use Optional methods:
   ```java
   Optional<Employee> emp = repository.findById(1);
   emp.ifPresent(e -> System.out.println(e.getName()));
   emp.ifPresentOrElse(
       e -> System.out.println(e.getName()),
       () -> System.out.println("Not found")
   );
   ```

---

### Issue 6: Application Crashes on Invalid Input

**Error Message:**
```
Exception in thread "main" java.lang.NumberFormatException: For input string: "abc"
```

**Cause:** User entered non-numeric input when integer was expected

**Solution:**

Wrap input in try-catch:

```java
private int readInt(String prompt) {
    try {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Invalid input! Please enter a number.");
        return readInt(prompt);  // Ask again
    }
}
```

---

### Issue 7: Build Failures with Dependencies

**Error Message:**
```
[ERROR] Failed to execute goal on project employee-management-system:
[ERROR] Could not resolve dependencies
```

**Cause:** Maven cannot download dependencies (network issue or corrupted cache)

**Solutions:**

1. Force update dependencies:
   ```bash
   mvn clean install -U
   ```

2. Clear Maven cache:
   ```bash
   # Windows
   rmdir /s %USERPROFILE%\.m2\repository

   # macOS/Linux
   rm -rf ~/.m2/repository
   ```

3. Check internet connection and try again:
   ```bash
   mvn clean compile
   ```

4. Use offline mode (if dependencies cached):
   ```bash
   mvn -o compile
   ```

---

### Issue 8: Port Already in Use (Future Reference)

**Error Message (if adding server later):**
```
java.net.BindException: Address already in use: bind
```

**Solution:**

Find and kill process using port:

**Windows:**
```bash
netstat -ano | findstr :8080
taskkill /PID <process_id> /F
```

**macOS/Linux:**
```bash
lsof -i :8080
kill -9 <PID>
```

---

### Issue 9: Test Failures

**Error Message:**
```
[ERROR] Tests run: 5, Failures: 2, Errors: 0, Skipped: 0
```

**Causes:**
- Test expectations don't match actual behavior
- Test data not initialized properly
- Mock objects not configured correctly

**Solution:**

1. Run failing test in verbose mode:
   ```bash
   mvn test -Dtest=EmployeeTest#testCreateEmployee -X
   ```

2. Check test output for details:
   ```bash
   mvn test -Dtest=EmployeeTest
   ```

3. Debug test:
   - Set breakpoints in test code
   - Run in debug mode from IDE
   - Inspect variables

---

### Issue 10: Encoding Issues (Special Characters)

**Error Message:**
```
? ? ? (garbled characters in output)
```

**Cause:** File encoding doesn't match system encoding

**Solution:**

1. Set UTF-8 encoding in Maven:
   ```bash
   mvn compile -Dproject.build.sourceEncoding=UTF-8
   ```

2. Add to pom.xml:
   ```xml
   <properties>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
   </properties>
   ```

3. In IDE, set file encoding to UTF-8:
   - VS Code: File → Preferences → Settings → Files: Encoding
   - IntelliJ: File → Settings → Editor → File Encodings

---

### Issue 11: OutOfMemoryError

**Error Message:**
```
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
```

**Cause:** Application needs more memory than allocated

**Solution:**

Increase heap memory:

```bash
# Windows
set MAVEN_OPTS=-Xmx512m
mvn clean package

# macOS/Linux
export MAVEN_OPTS=-Xmx512m
mvn clean package

# Run application with more memory
java -Xmx512m -jar target/employee-management.jar
```

---

### Issue 12: Scanner Not Closing

**Warning Message:**
```
Resource leak: 'scanner' is never closed
```

**Cause:** Scanner resource not properly closed

**Solution:**

```java
// In try-with-resources
try (Scanner scanner = new Scanner(System.in)) {
    // Use scanner
} // Auto-closes

// OR manually close
Scanner scanner = new Scanner(System.in);
try {
    // Use scanner
} finally {
    scanner.close();
}
```

---

### Issue 13: Inconsistent Line Endings (Git Issue)

**Error Message (on commit):**
```
warning: LF will be replaced by CRLF
```

**Solution:**

Configure Git:

```bash
# Normalize line endings
git config --global core.autocrlf true

# For this project only
git config core.autocrlf true
```

---

### Issue 14: Cannot Delete or Modify JAR File

**Error Message:**
```
Access Denied when trying to delete target/employee-management.jar
```

**Cause:** JAR is locked (application still running)

**Solution:**

1. Stop the running application (Ctrl+C)
2. Clean build:
   ```bash
   mvn clean
   ```
3. Rebuild:
   ```bash
   mvn package
   ```

---

### Issue 15: Import Statements Show Red Errors

**Error Message (in IDE):**
```
Cannot resolve symbol 'Employee'
```

**Causes:**
- Source code not compiled
- IDE not recognizing source directory
- Import statements incorrect

**Solutions:**

1. Compile project:
   ```bash
   mvn compile
   ```

2. Refresh IDE:
   - VS Code: File → Close Folder → Reopen
   - IntelliJ: File → Invalidate Caches → Restart

3. Fix imports:
   ```java
   import com.employeemanagement.Employee;  // Correct
   // NOT
   import Employee;  // Wrong - needs full path
   ```

---

## Debugging Workflow

### Step 1: Read Error Message Carefully

The error message usually tells you:
- **What** went wrong (exception type)
- **Where** it went wrong (file and line number)
- **Why** it went wrong (error details)

### Step 2: Search for Solution

1. Copy the error message into Google
2. Search on Stack Overflow
3. Check project documentation

### Step 3: Add Debug Output

```java
System.out.println("DEBUG: Variable value = " + variable);
System.out.println("DEBUG: Size of list = " + list.size());
```

### Step 4: Use IDE Debugger

1. Set breakpoint by clicking line number
2. Run in debug mode (F5 in VS Code, Shift+F9 in IntelliJ)
3. Step through code line by line
4. Inspect variables in Debug panel

### Step 5: Check Assumptions

Ask yourself:
- Is this variable null?
- Is this list empty?
- Is this value what I expect?
- Did I handle all error cases?

---

## Performance Issues

### Slow Compilation

```bash
# Use parallel build
mvn clean compile -T 1C

# Check plugin execution time
mvn clean compile -Dspeed=fast
```

### Slow Testing

```bash
# Run specific test class only
mvn test -Dtest=EmployeeTest

# Skip tests during build
mvn clean package -DskipTests
```

### Slow Application

```bash
# Profile memory usage
java -Xmx256m -jar target/employee-management.jar

# Profile CPU (if profiling tools installed)
jps -l
jcmd <pid> JFR.start
```

---

## Getting Help

### Online Resources

1. **Stack Overflow**: https://stackoverflow.com/
   - Tag your question with [java], [maven], [junit]
   - Include error message and minimal code

2. **Official Documentation**:
   - Java: https://docs.oracle.com/javase/11/
   - Maven: https://maven.apache.org/guides/

3. **GitHub Issues**:
   - Search for similar issues
   - Create new issue with details

### Asking for Help

When posting questions online, include:
1. What you tried to do
2. What error you got (full error message)
3. What you expected to happen
4. Minimal code example to reproduce

---

## Preventive Measures

### 1. Write Tests

```java
@Test
public void testCreateEmployee() {
    Employee emp = new Employee(1, "John", "IT", "Engineer", 50000);
    service.createEmployee(emp);
    
    Optional<Employee> result = service.getEmployeeById(1);
    assertTrue(result.isPresent());
    assertEquals("John", result.get().getName());
}
```

### 2. Use IDE Features

- Enable inspections (VS Code: Extensions → Code Intelligence)
- Enable formatting on save (VS Code: Settings → Format On Save)
- Use refactoring tools (Rename, Extract Method, etc.)

### 3. Follow Best Practices

- Use meaningful variable names
- Write JavaDoc comments
- Keep methods small and focused
- Handle exceptions properly
- Test edge cases

### 4. Version Control

```bash
git init
git add .
git commit -m "Initial commit"
# Commit frequently with meaningful messages
```

---

## Checklist for New Issues

- [ ] Verify Java version (11+)
- [ ] Verify Maven installed
- [ ] Run `mvn clean compile`
- [ ] Check file encodings (UTF-8)
- [ ] Verify directory structure
- [ ] Check package declarations
- [ ] Run individual tests
- [ ] Check IDE logs (View → Output in VS Code)
- [ ] Search Stack Overflow
- [ ] Try fresh clone if all else fails

---

## Quick Reference

| Issue | Quick Fix |
|-------|-----------|
| Command not found | Add to PATH environment variable |
| Cannot find symbol | `mvn clean compile` |
| NullPointerException | Check Optional before using |
| NumberFormatException | Wrap input in try-catch |
| Dependency error | `mvn clean install -U` |
| OutOfMemory | Increase heap: `-Xmx512m` |
| Port in use | `kill -9 <PID>` |

---

**Still stuck?** Check the [SETUP_AND_RUN_INSTRUCTIONS.md](../SETUP_AND_RUN_INSTRUCTIONS.md) or reach out to your instructor!

