# Logging Configuration

This application is configured to log to both console and file for better debugging and monitoring.

## Log Files

- **Location**: `logs/dashboard.log`
- **Rotation**: Logs are automatically rotated when they reach 10MB
- **Retention**: 30 days of log history
- **Format**: `YYYY-MM-DD HH:mm:ss.SSS [thread] LEVEL logger - message`

## Log Levels

- **INFO**: General application flow, API requests, YAML parsing
- **WARN**: Potential issues that don't stop execution
- **ERROR**: Errors that need attention
- **DEBUG**: Detailed debugging information (not enabled by default)

## Viewing Logs

### Using the provided script:
```bash
# View last 50 lines (default)
./view-logs.sh

# Follow logs in real-time
./view-logs.sh follow

# View all logs
./view-logs.sh all
```

### Using standard commands:
```bash
# View last 50 lines
tail -50 logs/dashboard.log

# Follow logs in real-time
tail -f logs/dashboard.log

# View all logs
cat logs/dashboard.log

# Search for specific patterns
grep "ERROR" logs/dashboard.log
grep "API request" logs/dashboard.log
```

## Log Categories

The application logs several types of events:

1. **Application Startup**: Spring Boot initialization
2. **Dashboard Page Requests**: When users visit the main page
3. **API Requests**: When applications are loaded via AJAX
4. **YAML Parsing**: Performance metrics for file parsing
5. **Configuration Loading**: Master config and individual app configs

## Configuration Files

- `src/main/resources/application.properties`: Basic logging settings
- `src/main/resources/logback-spring.xml`: Advanced logging configuration

## Log Rotation

Logs are automatically rotated based on:
- **Size**: When file reaches 10MB
- **Time**: Daily rotation with date in filename
- **History**: Keeps 30 days of logs

Example rotated files:
- `dashboard.log` (current)
- `dashboard.2025-06-20.0.log` (yesterday)
- `dashboard.2025-06-19.0.log` (day before) 