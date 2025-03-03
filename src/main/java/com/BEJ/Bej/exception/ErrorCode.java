package com.BEJ.Bej.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION (9999, "Uncategorized_exception_Error"),
    INVALID_KEY(10001, "Invalid message key"),
    USER_EXISTED (1002, "User existed"),
    USER_NOT_EXISTED (1004, "User not existed"),
    USERNAME_INVALID(1003, "Username must be at least 6 characters"),
    INVALID_PASSWORD(1006, "Password must be at least 8 characters"),

    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
