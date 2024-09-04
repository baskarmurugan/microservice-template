package org.template.userservice.exception;

import java.time.LocalDateTime;

public class CustomErrorResponse {
    private String guid;
    private String errorCode;
    private String message;
    private int statusCode;
    private String statusName;
    private String path;
    private String method;
    private LocalDateTime timestamp;

    public CustomErrorResponse(String guid, String errorCode, String message, int statusCode, String statusName, String path, String method, LocalDateTime timestamp) {
        this.guid = guid;
        this.errorCode = errorCode;
        this.message = message;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.path = path;
        this.method = method;
        this.timestamp = timestamp;
    }

    // Getters and Setters
}
