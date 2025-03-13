package com.javidev.todo_list_spring_react_backend.security.jwt;

import com.javidev.todo_list_spring_react_backend.persistence.model.AppUser;
import com.javidev.todo_list_spring_react_backend.persistence.repository.UserRepository;
import com.javidev.todo_list_spring_react_backend.security.CustomUserDetailsService;
import com.javidev.todo_list_spring_react_backend.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        System.out.println("🔹 URI solicitada: " + request.getRequestURI());
        System.out.println("🔹 Método HTTP: " + request.getMethod());

        if (token == null || token.isEmpty()) {
            System.out.println("❌ No se encontró token en la cabecera Authorization");
        } else {
            System.out.println("🔹 Token recibido: " + token);
        }

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            try {
                String username = jwtUtil.extractUsername(token);
                System.out.println("✅ Usuario autenticado: " + username);

                AppUser user = userRepository.findByEmail(username).orElseThrow();
                System.out.println("🔹 Rol del usuario autenticado en la BD: " + user.getRole());

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    if (jwtUtil.validateToken(token, userDetailsService.loadUserByUsername(username))) {
                        // Añadir el prefijo "ROLE_" a la autoridad
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()))
                        );
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("✅ Token validado correctamente.");
                        System.out.println("✅ Permisos asignados en SecurityContext: " + authentication.getAuthorities());
                    }
                }
            } catch (Exception e) {
                System.err.println("❌ Error procesando JWT: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

}
