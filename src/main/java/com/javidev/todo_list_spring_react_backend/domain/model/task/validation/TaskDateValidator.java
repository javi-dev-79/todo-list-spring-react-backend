package com.javidev.todo_list_spring_react_backend.domain.model.task.validation;

import com.javidev.todo_list_spring_react_backend.persistence.model.Task;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.ZoneId;

public class TaskDateValidator implements ConstraintValidator<ValidDateRange, Task> {

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(Task task, ConstraintValidatorContext context) {
        if (task.getEndDate() == null) {
            return true;
        }

        LocalDate taskDate = task.getEndDate().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();

        if (taskDate.isBefore(today)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End date must be today or in the future")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
