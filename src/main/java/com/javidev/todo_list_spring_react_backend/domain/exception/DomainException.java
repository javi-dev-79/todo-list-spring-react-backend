package com.javidev.todo_list_spring_react_backend.domain.exception;

public class DomainException extends RuntimeException {
    private final String errorCode;

    public DomainException(String message) {
        super(message);
        this.errorCode = "DOMAIN_ERROR";
    }

    public DomainException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
