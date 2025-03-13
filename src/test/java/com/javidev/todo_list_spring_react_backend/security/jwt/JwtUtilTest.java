package com.javidev.todo_list_spring_react_backend.security.jwt;

import com.javidev.todo_list_spring_react_backend.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    private JwtUtil jwtUtil;

    @Mock
    private UserDetails mockUserDetails;

    private String token;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil("mysecretkeymysecretkeymysecretkey", 3600L); // Clave de 32+ caracteres

        when(mockUserDetails.getUsername()).thenReturn("testuser");

        token = jwtUtil.generateToken(mockUserDetails);
    }

    @Test
    void testGenerateToken() {
        assertNotNull(token, "El token generado no debe ser nulo");
        assertFalse(token.isEmpty(), "El token no debe estar vacío");
    }

    @Test
    void testValidateToken() {
        boolean isValid = jwtUtil.validateToken(token, mockUserDetails);
        assertTrue(isValid, "El token debe ser válido para el usuario dado");
    }

    @Test
    void testExtractUsername() {
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username, "El nombre de usuario extraído debe coincidir con el esperado");
    }

    @Test
    void testExtractExpiration() {
        Date expirationDate = jwtUtil.extractExpiration(token);
        assertNotNull(expirationDate, "La fecha de expiración no debe ser nula");
        assertTrue(expirationDate.after(new Date()), "La fecha de expiración debe estar en el futuro");
    }

    @Test
    void testTokenWithInvalidUser() {
        UserDetails anotherUser = mock(UserDetails.class);
        when(anotherUser.getUsername()).thenReturn("anotheruser");

        boolean isValid = jwtUtil.validateToken(token, anotherUser);
        assertFalse(isValid, "El token no debe ser válido para un usuario diferente");
    }
}
