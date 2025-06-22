# Spring Boot Dashboard - Windows Troubleshooting Script
# Run this script in PowerShell as Administrator if needed

Write-Host "========================================" -ForegroundColor Green
Write-Host "Spring Boot Dashboard - Troubleshooting" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

# Function to check if command exists
function Test-Command($cmdname) {
    return [bool](Get-Command -Name $cmdname -ErrorAction SilentlyContinue)
}

# Check Java
Write-Host "Checking Java installation..." -ForegroundColor Yellow
if (Test-Command "java") {
    $javaVersion = java -version 2>&1 | Select-String "version"
    Write-Host "✓ Java is installed: $javaVersion" -ForegroundColor Green
} else {
    Write-Host "✗ Java is not installed or not in PATH" -ForegroundColor Red
}

if ($env:JAVA_HOME) {
    Write-Host "✓ JAVA_HOME is set: $env:JAVA_HOME" -ForegroundColor Green
} else {
    Write-Host "✗ JAVA_HOME is not set" -ForegroundColor Red
}

Write-Host ""

# Check Maven
Write-Host "Checking Maven installation..." -ForegroundColor Yellow
if (Test-Command "mvn") {
    $mvnVersion = mvn -version | Select-String "Apache Maven"
    Write-Host "✓ Maven is installed: $mvnVersion" -ForegroundColor Green
} else {
    Write-Host "✗ Maven is not installed or not in PATH" -ForegroundColor Red
}

if ($env:MAVEN_HOME) {
    Write-Host "✓ MAVEN_HOME is set: $env:MAVEN_HOME" -ForegroundColor Green
} else {
    Write-Host "✗ MAVEN_HOME is not set" -ForegroundColor Red
}

Write-Host ""

# Check Maven Wrapper
Write-Host "Checking Maven Wrapper..." -ForegroundColor Yellow
if (Test-Path "mvnw.cmd") {
    Write-Host "✓ Maven wrapper (mvnw.cmd) exists" -ForegroundColor Green
} else {
    Write-Host "✗ Maven wrapper (mvnw.cmd) not found" -ForegroundColor Red
}

Write-Host ""

# Check port usage
Write-Host "Checking port 8080..." -ForegroundColor Yellow
$port8080 = netstat -ano | findstr ":8080"
if ($port8080) {
    Write-Host "✗ Port 8080 is in use:" -ForegroundColor Red
    Write-Host $port8080 -ForegroundColor Red
    Write-Host "You may need to kill the process or change the port in application.properties" -ForegroundColor Yellow
} else {
    Write-Host "✓ Port 8080 is available" -ForegroundColor Green
}

Write-Host ""

# Check project structure
Write-Host "Checking project structure..." -ForegroundColor Yellow
$requiredFiles = @(
    "pom.xml",
    "src\main\java\com\example\dashboard\DashboardApplication.java",
    "src\main\resources\application.properties",
    "src\main\resources\application-list.yaml",
    "src\main\resources\applications\fenergo.yaml"
)

foreach ($file in $requiredFiles) {
    if (Test-Path $file) {
        Write-Host "✓ $file exists" -ForegroundColor Green
    } else {
        Write-Host "✗ $file missing" -ForegroundColor Red
    }
}

Write-Host ""

# Check YAML syntax
Write-Host "Checking YAML files..." -ForegroundColor Yellow
$yamlFiles = @(
    "src\main\resources\application-list.yaml",
    "src\main\resources\applications\fenergo.yaml",
    "src\main\resources\applications\risk-analyzer.yaml",
    "src\main\resources\applications\trade-flow.yaml"
)

foreach ($yamlFile in $yamlFiles) {
    if (Test-Path $yamlFile) {
        try {
            $content = Get-Content $yamlFile -Raw
            # Basic YAML validation - check for proper indentation
            if ($content -match "\t") {
                Write-Host "⚠ $yamlFile contains tabs (should use spaces)" -ForegroundColor Yellow
            } else {
                Write-Host "✓ $yamlFile syntax looks good" -ForegroundColor Green
            }
        } catch {
            Write-Host "✗ Error reading $yamlFile" -ForegroundColor Red
        }
    }
}

Write-Host ""

# Check logs directory
Write-Host "Checking logs..." -ForegroundColor Yellow
if (Test-Path "logs") {
    Write-Host "✓ Logs directory exists" -ForegroundColor Green
    if (Test-Path "logs\dashboard.log") {
        $logSize = (Get-Item "logs\dashboard.log").Length
        Write-Host "✓ Log file exists (size: $logSize bytes)" -ForegroundColor Green
    } else {
        Write-Host "⚠ Log file doesn't exist yet (will be created when app runs)" -ForegroundColor Yellow
    }
} else {
    Write-Host "⚠ Logs directory doesn't exist (will be created when app runs)" -ForegroundColor Yellow
}

Write-Host ""

# Provide recommendations
Write-Host "Recommendations:" -ForegroundColor Cyan
if (-not (Test-Command "java")) {
    Write-Host "- Install Java 11 or 17 from https://adoptium.net/" -ForegroundColor White
}
if (-not (Test-Command "mvn")) {
    Write-Host "- Install Maven from https://maven.apache.org/download.cgi" -ForegroundColor White
}
if (-not $env:JAVA_HOME) {
    Write-Host "- Set JAVA_HOME environment variable" -ForegroundColor White
}
if ($port8080) {
    Write-Host "- Change server.port in application.properties to 8081" -ForegroundColor White
}

Write-Host ""
Write-Host "To run the application:" -ForegroundColor Cyan
Write-Host "1. mvn clean install" -ForegroundColor White
Write-Host "2. mvn spring-boot:run" -ForegroundColor White
Write-Host "3. Open http://localhost:8080 in browser" -ForegroundColor White

Write-Host ""
Write-Host "Press any key to exit..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown") 