package com.javidev.todo_list_spring_react_backend.domain.service;

import com.javidev.todo_list_spring_react_backend.domain.exception.NonExistingEntityException;
import com.javidev.todo_list_spring_react_backend.domain.exception.UnauthorizedAccessException;
import com.javidev.todo_list_spring_react_backend.domain.model.auth.LoginResponse;
import com.javidev.todo_list_spring_react_backend.persistence.model.AppUser;
import com.javidev.todo_list_spring_react_backend.persistence.model.Role;
import com.javidev.todo_list_spring_react_backend.persistence.repository.UserRepository;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.mapper.AuthMapper;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.LoginRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.RegisterRequestBody;
import com.javidev.todo_list_spring_react_backend.presentation.controller.auth.model.RegisterResponse;
import com.javidev.todo_list_spring_react_backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthMapper authMapper;

    public RegisterResponse register(RegisterRequestBody request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        AppUser user = AppUser.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        AppUser registeredUser = userRepository.save(user);

        String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(registeredUser.getEmail()));

        return authMapper.toRegisterResponse("Usuario registrado con éxito", user.getEmail(), token);
    }

    public LoginResponse login(LoginRequestBody credentials) {
        var email = credentials.getEmail();
        var password = credentials.getPassword();
        try {
            System.out.println("🔹 Intentando autenticar usuario: " + email);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            System.out.println("✅ Autenticación exitosa para usuario: " + email);

            var userDetails = userDetailsService.loadUserByUsername(email);
            var token = jwtUtil.generateToken(userDetails);

            var user = userRepository.findByEmail(email).orElseThrow(() -> new NonExistingEntityException(AppUser.class, email));
            var role = user.getRole().toString();
            System.out.println("🔹 Usuario autenticado: " + email + ", Rol: " + role);

            return LoginResponse.builder()
                    .token(token)
                    .role(role)
                    .build();

        } catch (Exception e) {
            log.error("❌ Error en autenticación: {}", e.getMessage());
            throw new UnauthorizedAccessException("Error de autenticación");
        }
    }

}
