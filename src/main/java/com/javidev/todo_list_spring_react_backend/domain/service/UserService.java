package com.javidev.todo_list_spring_react_backend.domain.service;

import com.javidev.todo_list_spring_react_backend.domain.exception.NonExistingEntityException;
import com.javidev.todo_list_spring_react_backend.domain.model.user.CreateUserParameters;
import com.javidev.todo_list_spring_react_backend.domain.model.user.UpdateUserParameters;
import com.javidev.todo_list_spring_react_backend.persistence.model.AppUser;
import com.javidev.todo_list_spring_react_backend.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NonExistingEntityException(AppUser.class, id));
    }

    @Transactional
    public AppUser createUser(CreateUserParameters parameters) {
        var user = AppUser.builder()
                .name(parameters.getName())
                .email(parameters.getEmail())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public AppUser updateUser(UUID id, UpdateUserParameters updateUserParameters) {
        var existingUser = getUserById(id);
        existingUser.setEmail(updateUserParameters.getEmail());
        existingUser.setName(updateUserParameters.getName());
        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
