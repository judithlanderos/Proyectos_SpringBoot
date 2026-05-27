package com.example.auth_service.services;
import com.example.auth_service.models.UsuarioModel;
import com.example.auth_service.repositories.UsuarioRepository;
import com.example.auth_service.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String username, String password) {
        Optional<UsuarioModel> optional = usuarioRepository.findByUsername(username);
        if (optional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        UsuarioModel usuario = optional.get();
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return jwtUtil.generarToken(usuario.getUsername(), usuario.getRol());
    }

    public boolean validarToken(String token) {
        return jwtUtil.validarToken(token);
    }

    public void registrar(UsuarioModel usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if (usuario.getRol() == null) usuario.setRol("USER");
        usuarioRepository.save(usuario);
    }
    
}
