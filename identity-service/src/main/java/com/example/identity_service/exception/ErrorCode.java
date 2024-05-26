package com.example.identity_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    INVALID_KEY(1002, "Invalid message key"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    USERNAME_PASSWORD(1004, "Userpassword must be at least 8 characters")
    ;

    private int code;
    private String message;

}
