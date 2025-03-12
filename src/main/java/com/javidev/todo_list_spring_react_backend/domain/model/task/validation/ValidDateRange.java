package com.javidev.todo_list_spring_react_backend.domain.model.task.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TaskDateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {

    String message() default "The end date must be in the future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}