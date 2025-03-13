package com.javidev.todo_list_spring_react_backend.presentation.controller;

import com.javidev.todo_list_spring_react_backend.domain.service.AuthService;
import com.javidev.todo_list_spring_react_backend.persistence.model.AppUser;
import com.javidev.todo_list_spring_react_backend.persistence.repository.UserRepository;
import com.javidev.todo_list_spring_react_backend.presentation.controller.user.model.RegisterRequest;
import com.javidev.todo_list_spring_react_backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public Map<String, String> authenticate(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        try {

            System.out.println("🔹 Intentando autenticar usuario: " + email);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            System.out.println("✅ Autenticación exitosa para usuario: " + email);

            var userDetails = userDetailsService.loadUserByUsername(email);
            String token = jwtUtil.generateToken(userDetails);

            AppUser user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            String role = user.getRole().toString();
            System.out.println("🔹 Usuario autenticado: " + email + ", Rol: " + role);

            return Map.of("token", token, "role", role);
        } catch (Exception e) {
            System.err.println("❌ Error en autenticación: " + e.getMessage());
            return Map.of("token", e.getMessage());
        }
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterRequest request) {
        log.info("Solicitud de registro recibida para: {}", request.getEmail());
        authService.register(request);
        return Map.of("message", "Usuario registrado con éxito");
    }

}