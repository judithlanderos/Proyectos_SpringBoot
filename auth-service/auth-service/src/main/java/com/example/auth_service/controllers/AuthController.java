package com.example.auth_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.TokenResponse;
import com.example.auth_service.models.UsuarioModel;
import com.example.auth_service.services.AuthService;

@Controller
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new TokenResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody UsuarioModel usuario) {
        try {
            authService.registrar(usuario);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/validar")
    public ResponseEntity<?> validar(@RequestParam String token) {
        boolean valido = authService.validarToken(token);
        return valido ? ResponseEntity.ok("Token válido")
                      : ResponseEntity.status(401).body("Token inválido");
    }

    @GetMapping("/registro-form")
public String mostrarRegistro() {
    return "registro";
}

@PostMapping("/registro-form")
public String procesarRegistro(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String rol,
                                Model model) {
    try {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setRol(rol);
        authService.registrar(usuario);
        model.addAttribute("exito", "Usuario registrado correctamente");
        return "registro";
    } catch (Exception e) {
        model.addAttribute("error", "Error: " + e.getMessage());
        return "registro";
    }
}
}
