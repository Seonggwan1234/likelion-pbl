package com.likelion.mission10.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String code;
    private final String message;

    public ErrorResponse(int status, String code, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getCode() { return code; }
    public String getMessage() { return message; }
}
