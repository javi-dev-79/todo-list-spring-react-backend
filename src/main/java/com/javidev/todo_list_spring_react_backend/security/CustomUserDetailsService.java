package com.javidev.todo_list_spring_react_backend.security;

import com.javidev.todo_list_spring_react_backend.persistence.model.AppUser;
import com.javidev.todo_list_spring_react_backend.persistence.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        System.out.println("✅ Usuario encontrado en la BD: " + user.getEmail() + ", Rol: " + user.getRole());

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))) // 🔹 Agregar "ROLE_"
                .build();
    }

}
