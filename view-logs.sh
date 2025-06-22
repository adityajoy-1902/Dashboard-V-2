#!/bin/bash

# Script to view dashboard logs
# Usage: ./view-logs.sh [follow|tail|all]

LOG_FILE="logs/dashboard.log"

if [ ! -f "$LOG_FILE" ]; then
    echo "Log file not found: $LOG_FILE"
    echo "Make sure the application is running and has generated logs."
    exit 1
fi

case "${1:-tail}" in
    "follow"|"f")
        echo "Following logs in real-time (Ctrl+C to stop)..."
        tail -f "$LOG_FILE"
        ;;
    "tail"|"t")
        echo "Last 50 lines of logs:"
        tail -50 "$LOG_FILE"
        ;;
    "all"|"a")
        echo "All logs:"
        cat "$LOG_FILE"
        ;;
    *)
        echo "Usage: $0 [follow|tail|all]"
        echo "  follow - Follow logs in real-time"
        echo "  tail   - Show last 50 lines (default)"
        echo "  all    - Show all logs"
        ;;
esac 