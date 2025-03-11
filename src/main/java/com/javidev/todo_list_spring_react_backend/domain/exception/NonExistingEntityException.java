package com.javidev.todo_list_spring_react_backend.domain.exception;

public class NonExistingEntityException extends DomainException {

    public NonExistingEntityException(Class<?> aClass) {
        super("%s does not exist".formatted(aClass.getSimpleName()));
    }

    public <T, ID> NonExistingEntityException(Class<T> aClass, ID id) {
        super("%s with id: %s does not exist".formatted(aClass.getSimpleName(), id));
    }
}
