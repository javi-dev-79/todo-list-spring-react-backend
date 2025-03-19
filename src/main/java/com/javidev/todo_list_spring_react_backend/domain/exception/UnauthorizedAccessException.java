package com.javidev.todo_list_spring_react_backend.domain.exception;

public class UnauthorizedAccessException extends DomainException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
