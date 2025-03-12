package com.javidev.todo_list_spring_react_backend.presentation.controller.error;

import com.javidev.todo_list_spring_react_backend.domain.exception.NonExistingEntityException;
import com.javidev.todo_list_spring_react_backend.presentation.controller.error.model.ErrorResponse;
import com.javidev.todo_list_spring_react_backend.presentation.controller.error.model.FieldValidationError;
import com.javidev.todo_list_spring_react_backend.presentation.controller.error.model.ValidationErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NonExistingEntityException.class)
    public ResponseEntity<ErrorResponse> handleNonExistingEntity(NonExistingEntityException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return buildErrorResponse(httpStatus.value(), ex.getMessage(), httpStatus);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return buildErrorResponse(httpStatus.value(), ex.getMessage(), httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<FieldValidationError> fieldErrors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            FieldValidationError existingFieldError = fieldErrors.stream()
                    .filter(fieldError -> fieldError.getField().equals(error.getField()))
                    .findFirst()
                    .orElseGet(() -> {
                        FieldValidationError newFieldError = FieldValidationError.builder()
                                .field(error.getField())
                                .errors(new ArrayList<>())
                                .build();
                        fieldErrors.add(newFieldError);
                        return newFieldError;
                    });

            existingFieldError.getErrors().add(error.getDefaultMessage());
        }

        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .code(httpStatus.value())
                .validations(fieldErrors)
                .build();

        return new ResponseEntity<>(response, createJsonHeaders(), httpStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return new ResponseEntity<>(errors, createJsonHeaders(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(int errorCode, String errorMessage, HttpStatus httpStatus) {
        ErrorResponse response = ErrorResponse.builder()
                .code(errorCode)
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(response, createJsonHeaders(), httpStatus);
    }

    private HttpHeaders createJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}