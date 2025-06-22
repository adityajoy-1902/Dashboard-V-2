# Windows Setup and Troubleshooting Guide

## Prerequisites

### 1. Java Installation
- **Download**: Install Java 11 or 17 from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
- **Set JAVA_HOME**: 
  ```cmd
  setx JAVA_HOME "C:\Program Files\Java\jdk-11.0.x"
  setx PATH "%PATH%;%JAVA_HOME%\bin"
  ```
- **Verify**: `java -version` and `javac -version`

### 2. Maven Installation (Alternative to Maven Wrapper)
- **Download**: [Apache Maven](https://maven.apache.org/download.cgi)
- **Extract**: To `C:\Program Files\Apache\maven`
- **Set MAVEN_HOME**:
  ```cmd
  setx MAVEN_HOME "C:\Program Files\Apache\maven"
  setx PATH "%PATH%;%MAVEN_HOME%\bin"
  ```
- **Verify**: `mvn -version`

## Common Issues and Solutions

### Issue 1: Maven Wrapper Not Recognized
**Error**: `'mvnw.cmd' is not recognized as an internal or external command`

**Solutions**:
1. **Use Maven directly**:
   ```cmd
   mvn clean install
   mvn spring-boot:run
   ```

2. **Fix Maven Wrapper**:
   ```cmd
   # Run from project root
   mvnw.cmd clean install
   mvnw.cmd spring-boot:run
   ```

3. **If mvnw.cmd doesn't work**:
   ```cmd
   # Delete and regenerate wrapper
   rmdir /s .mvn
   del mvnw.cmd
   mvn wrapper:wrapper
   ```

### Issue 2: Port Already in Use
**Error**: `Web server failed to start. Port 8080 was already in use`

**Solutions**:
1. **Find and kill process**:
   ```cmd
   netstat -ano | findstr :8080
   taskkill /PID <PID> /F
   ```

2. **Change port** (edit `src/main/resources/application.properties`):
   ```properties
   server.port=8081
   ```

### Issue 3: File Path Issues
**Error**: `FileNotFoundException` or path-related errors

**Solutions**:
1. **Check file paths** - Windows uses backslashes:
   ```cmd
   # Verify YAML files exist
   dir src\main\resources\applications\
   ```

2. **Use forward slashes in code** (already done in the project)

### Issue 4: Java Version Compatibility
**Error**: `UnsupportedClassVersionError`

**Solutions**:
1. **Check Java version**:
   ```cmd
   java -version
   javac -version
   ```

2. **Ensure Java 11+ is installed and JAVA_HOME is set correctly**

### Issue 5: Lombok Issues
**Error**: `Cannot find symbol` for Lombok annotations

**Solutions**:
1. **Enable annotation processing in IDE**:
   - IntelliJ: Settings → Build Tools → Compiler → Annotation Processors → Enable
   - Eclipse: Install Lombok plugin

2. **Clean and rebuild**:
   ```cmd
   mvn clean compile
   ```

## Step-by-Step Setup

### Option 1: Using Maven Directly (Recommended)
```cmd
# 1. Clone or download project
git clone https://github.com/adityajoy-1902/Dashboard-V-2.git
cd Dashboard-V-2

# 2. Build project
mvn clean install

# 3. Run application
mvn spring-boot:run
```

### Option 2: Using Maven Wrapper
```cmd
# 1. Navigate to project directory
cd Dashboard-V-2

# 2. Build project
mvnw.cmd clean install

# 3. Run application
mvnw.cmd spring-boot:run
```

### Option 3: Using IDE (IntelliJ IDEA)
1. **Open project** in IntelliJ IDEA
2. **Import as Maven project**
3. **Wait for dependencies to download**
4. **Run** `DashboardApplication.java`

## Verification

### 1. Check Application Startup
Look for these log messages:
```
Started DashboardApplication in X.XXX seconds
Tomcat started on port(s): 8080 (http)
```

### 2. Access Dashboard
- Open browser: `http://localhost:8080`
- Should see the dashboard with application tabs

### 3. Check Logs
- Application logs: `logs/dashboard.log`
- Console output should show successful startup

## Troubleshooting Commands

### Check Java Installation
```cmd
java -version
javac -version
echo %JAVA_HOME%
```

### Check Maven Installation
```cmd
mvn -version
echo %MAVEN_HOME%
```

### Check Port Usage
```cmd
netstat -ano | findstr :8080
```

### Clean and Rebuild
```cmd
mvn clean install
mvn spring-boot:run
```

### View Logs
```cmd
type logs\dashboard.log
```

## Environment Variables Setup

### For Current Session
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-11.0.x
set MAVEN_HOME=C:\Program Files\Apache\maven
set PATH=%PATH%;%JAVA_HOME%\bin;%MAVEN_HOME%\bin
```

### For Permanent Setup
```cmd
setx JAVA_HOME "C:\Program Files\Java\jdk-11.0.x"
setx MAVEN_HOME "C:\Program Files\Apache\maven"
setx PATH "%PATH%;%JAVA_HOME%\bin;%MAVEN_HOME%\bin"
```

## IDE-Specific Setup

### IntelliJ IDEA
1. **File** → **Open** → Select project folder
2. **Import as Maven project**
3. **Settings** → **Build Tools** → **Compiler** → **Annotation Processors** → **Enable**
4. **Run** → **Edit Configurations** → **Add** → **Spring Boot**
5. **Main class**: `com.example.dashboard.DashboardApplication`

### Eclipse
1. **File** → **Import** → **Maven** → **Existing Maven Projects**
2. **Install Lombok plugin**
3. **Project** → **Clean**
4. **Run** → **Run Configurations** → **Spring Boot App**

## Common Error Messages and Solutions

| Error | Solution |
|-------|----------|
| `'mvnw.cmd' is not recognized` | Use `mvn` instead or fix PATH |
| `Port 8080 already in use` | Change port in `application.properties` |
| `UnsupportedClassVersionError` | Install Java 11+ and set JAVA_HOME |
| `Cannot find symbol @Data` | Enable Lombok annotation processing |
| `FileNotFoundException` | Check file paths and YAML syntax |
| `YAML parsing error` | Fix YAML indentation (use spaces, not tabs) |

## Support

If you continue to face issues:
1. Check the logs in `logs/dashboard.log`
2. Verify all prerequisites are installed correctly
3. Try running with Maven directly instead of wrapper
4. Ensure no antivirus is blocking the application 