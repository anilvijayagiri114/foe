# Employee Management System - Complete Setup and Execution Guide

This guide walks you through setting up and running the Employee Management System from scratch.

## 📋 Table of Contents
1. [Prerequisites](#prerequisites)
2. [Step-by-Step Setup](#step-by-step-setup)
3. [Running the Application](#running-the-application)
4. [Building with Maven](#building-with-maven)
5. [Troubleshooting](#troubleshooting)
6. [IDE Setup (VS Code/IntelliJ)](#ide-setup)

---

## Prerequisites

### 1. Java Development Kit (JDK)

**Check if Java is installed:**
```bash
java -version
```

**If not installed:**
- **Windows**: Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- **MacOS**: `brew install openjdk@11`
- **Linux**: `sudo apt-get install openjdk-11-jdk`

**Minimum Required**: Java 11 or higher
**Recommended**: Java 17 LTS

### 2. Maven

**Check if Maven is installed:**
```bash
mvn -version
```

**If not installed:**
- **Windows**: Download from [Maven Official Site](https://maven.apache.org/download.cgi)
- **MacOS**: `brew install maven`
- **Linux**: `sudo apt-get install maven`

**Set up Maven:**
1. Extract Maven to a directory (e.g., `C:\Program Files\Maven`)
2. Add Maven to PATH environment variable
3. Verify: `mvn -version`

### 3. Git (Optional but Recommended)

```bash
git --version
```

Install from [Git Official Site](https://git-scm.com/downloads)

### 4. IDE (Any One)
- **VS Code** (Free, lightweight)
- **IntelliJ IDEA Community Edition** (Free, powerful)
- **Eclipse IDE** (Free)

---

## Step-by-Step Setup

### Step 1: Download/Clone the Project

**Option A: Using Git (Recommended)**
```bash
git clone https://github.com/yourusername/employee-management.git
cd employee-management
```

**Option B: Download as ZIP**
1. Download the project ZIP file
2. Extract to your preferred location
3. Open terminal/command prompt in that directory

### Step 2: Verify Project Structure

```bash
# Navigate to project directory
cd employee-management

# List files (Windows)
dir

# List files (Mac/Linux)
ls -la
```

You should see:
```
pom.xml
README.md
SETUP_AND_RUN_INSTRUCTIONS.md
src/
docs/
resources/
```

### Step 3: Verify JDK and Maven

```bash
# Check Java version (should be 11+)
java -version

# Check Maven installation
mvn -version

# Set JAVA_HOME (if needed)
# Windows: set JAVA_HOME=C:\Program Files\Java\jdk-11
# Mac/Linux: export JAVA_HOME=/Library/Java/JavaVirtualMachines/openjdk-11.jdk/Contents/Home
```

### Step 4: Download Dependencies

Maven will automatically download dependencies. This may take 2-5 minutes on first run.

```bash
# Test Maven setup
mvn -v

# Validate project
mvn validate
```

---

## Running the Application

### Method 1: Run Directly from Source (Fastest)

```bash
# Compile source files
mvn compile

# Run the application
mvn exec:java -Dexec.mainClass="com.employeemanagement.EmployeeManagementApp"
```

### Method 2: Build JAR and Run (Recommended)

**Step 1: Create executable JAR**
```bash
mvn clean package
```

This creates: `target/employee-management.jar`

**Step 2: Run the JAR**
```bash
java -jar target/employee-management.jar
```

### Method 3: Run Tests First

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=EmployeeServiceTest

# Run tests with coverage report
mvn clean test
```

### Method 4: Using IDE

#### VS Code:
1. Install "Extension Pack for Java" from VS Code marketplace
2. Open project in VS Code
3. Press `Ctrl+Shift+D` to run
4. Select "Run" configuration

#### IntelliJ IDEA:
1. Open project → Select `pom.xml`
2. Right-click `EmployeeManagementApp.java`
3. Click "Run" or press `Shift+F10`

---

## Building with Maven

### Common Maven Commands

```bash
# Clean build artifacts
mvn clean

# Compile source code
mvn compile

# Run tests
mvn test

# Package application (create JAR)
mvn package

# Clean and package
mvn clean package

# Skip tests during package
mvn clean package -DskipTests

# Install to local repository
mvn install

# View project dependencies
mvn dependency:tree

# Run specific goal
mvn compile exec:java
```

### Understanding Maven Output

**Successful Build:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: 2.345 s
[INFO] Finished at: 2024-01-15T10:30:45+05:30
```

**Failed Build:**
```
[ERROR] BUILD FAILURE
[ERROR] For more information about the errors and re-run Maven with the -e switch
```

---

## Troubleshooting

### Issue 1: "Java is not recognized"

**Solution:**
```bash
# Windows: Set JAVA_HOME
set JAVA_HOME=C:\Program Files\Java\jdk-11

# Mac/Linux: Set JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk

# Verify
java -version
```

### Issue 2: "Maven is not recognized"

**Solution:**
```bash
# Windows: Add Maven to PATH
set PATH=%PATH%;C:\Program Files\Maven\bin

# Verify
mvn -version
```

### Issue 3: "Cannot find symbol" during compilation

**Solution:**
```bash
# Update Maven dependencies
mvn clean install -U

# This forces Maven to download latest versions
```

### Issue 4: "Tests failing"

**Solution:**
```bash
# Skip tests during build
mvn clean package -DskipTests

# Run tests individually
mvn test -Dtest=EmployeeTest
```

### Issue 5: "Out of memory" error

**Solution:**
```bash
# Windows
set MAVEN_OPTS=-Xmx1024m

# Mac/Linux
export MAVEN_OPTS=-Xmx1024m
```

### Issue 6: JAR file won't execute

**Solution:**
```bash
# Verify JAR creation
jar tf target/employee-management.jar

# Run with explicit classpath
java -cp target/employee-management.jar com.employeemanagement.EmployeeManagementApp
```

---

## IDE Setup

### VS Code Setup

**1. Install Extensions:**
- Extension Pack for Java
- Maven for Java
- Spring Boot Extension Pack (optional)

**2. Open Workspace:**
```bash
code employee-management
```

**3. Configure JDK:**
- Press `Ctrl+Shift+P`
- Search "Java: Configure Runtime"
- Select JDK 11+

**4. Run Application:**
- Open `EmployeeManagementApp.java`
- Click "Run" above main() method

### IntelliJ IDEA Setup

**1. Open Project:**
- File → Open
- Select project folder
- Choose "Open as Project"

**2. Configure JDK:**
- File → Project Structure
- Select "Project" → SDK
- Choose Java 11+

**3. Enable Maven:**
- View → Tool Windows → Maven
- Click "Execute Maven Goal"

**4. Run Application:**
- Right-click `EmployeeManagementApp.java`
- Click "Run EmployeeManagementApp"

---

## Advanced Setup

### Building for Distribution

```bash
# Create fat JAR (includes all dependencies)
mvn clean package assembly:single

# Result: target/employee-management.jar (standalone executable)
```

### Generate JavaDoc

```bash
# Generate API documentation
mvn javadoc:javadoc

# Open documentation
# On Windows: start target/site/apidocs/index.html
# On Mac: open target/site/apidocs/index.html
# On Linux: firefox target/site/apidocs/index.html
```

### Continuous Integration

```bash
# Run with Maven Enforcer (strict checks)
mvn clean verify

# Run with Code Coverage
mvn clean test jacoco:report
```

---

## Performance Tips

1. **Use Maven Daemon (faster builds):**
   ```bash
   mvn -T 1C clean package
   ```

2. **Skip unnecessary modules:**
   ```bash
   mvn -am -pl :employee-management-system clean package
   ```

3. **Use offline mode (after first download):**
   ```bash
   mvn -o clean package
   ```

---

## Next Steps

After successfully running the application:

1. **Explore the code**: Read through the source files
2. **Modify functionality**: Try adding new features
3. **Write tests**: Add unit tests for new code
4. **Integrate AI**: Set up API keys for AI features
5. **Deploy**: Learn about packaging and distribution

---

## Quick Reference Card

```bash
# Setup
git clone <repo-url>
cd employee-management

# Verify
java -version        # Should be 11+
mvn -version        # Should be 3.6+

# Build
mvn clean compile   # Compile source
mvn test            # Run tests
mvn clean package   # Create JAR

# Run
java -jar target/employee-management.jar
mvn exec:java -Dexec.mainClass="com.employeemanagement.EmployeeManagementApp"

# Troubleshoot
mvn clean install -U    # Force update dependencies
mvn clean package -DskipTests  # Skip tests
mvn dependency:tree     # View dependencies
```

---

## Getting Help

- **Maven Documentation**: https://maven.apache.org/
- **Java Documentation**: https://docs.oracle.com/javase/11/
- **Stack Overflow**: Tag questions with `java` and `maven`
- **Project Issues**: Check GitHub issues page

---

**Good luck with your project! Happy coding! 🚀**
