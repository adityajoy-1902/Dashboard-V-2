@echo off
echo ========================================
echo Spring Boot Dashboard - Windows Runner
echo ========================================
echo.

:menu
echo Choose an option:
echo 1. Build and run application (Maven)
echo 2. Build and run application (Maven Wrapper)
echo 3. Check Java installation
echo 4. Check Maven installation
echo 5. Check port usage
echo 6. View application logs
echo 7. Clean and rebuild
echo 8. Exit
echo.
set /p choice="Enter your choice (1-8): "

if "%choice%"=="1" goto run_maven
if "%choice%"=="2" goto run_wrapper
if "%choice%"=="3" goto check_java
if "%choice%"=="4" goto check_maven
if "%choice%"=="5" goto check_port
if "%choice%"=="6" goto view_logs
if "%choice%"=="7" goto clean_rebuild
if "%choice%"=="8" goto exit
echo Invalid choice. Please try again.
goto menu

:run_maven
echo.
echo Building and running with Maven...
echo.
mvn clean install
if %errorlevel% neq 0 (
    echo Build failed! Please check the errors above.
    pause
    goto menu
)
echo.
echo Build successful! Starting application...
echo.
mvn spring-boot:run
goto menu

:run_wrapper
echo.
echo Building and running with Maven Wrapper...
echo.
mvnw.cmd clean install
if %errorlevel% neq 0 (
    echo Build failed! Please check the errors above.
    echo Try using option 1 (Maven) instead.
    pause
    goto menu
)
echo.
echo Build successful! Starting application...
echo.
mvnw.cmd spring-boot:run
goto menu

:check_java
echo.
echo Checking Java installation...
echo.
java -version
if %errorlevel% neq 0 (
    echo Java is not installed or not in PATH!
    echo Please install Java 11 or 17 and set JAVA_HOME.
) else (
    echo Java is installed correctly.
)
echo.
echo JAVA_HOME: %JAVA_HOME%
echo.
pause
goto menu

:check_maven
echo.
echo Checking Maven installation...
echo.
mvn -version
if %errorlevel% neq 0 (
    echo Maven is not installed or not in PATH!
    echo Please install Maven and set MAVEN_HOME.
) else (
    echo Maven is installed correctly.
)
echo.
echo MAVEN_HOME: %MAVEN_HOME%
echo.
pause
goto menu

:check_port
echo.
echo Checking if port 8080 is in use...
echo.
netstat -ano | findstr :8080
if %errorlevel% neq 0 (
    echo Port 8080 is available.
) else (
    echo Port 8080 is in use!
    echo You may need to kill the process or change the port.
)
echo.
pause
goto menu

:view_logs
echo.
echo Viewing application logs...
echo.
if exist "logs\dashboard.log" (
    type logs\dashboard.log
) else (
    echo No log file found at logs\dashboard.log
)
echo.
pause
goto menu

:clean_rebuild
echo.
echo Cleaning and rebuilding project...
echo.
mvn clean
mvn compile
mvn test
echo.
echo Clean and rebuild completed!
echo.
pause
goto menu

:exit
echo.
echo Goodbye!
exit /b 0 