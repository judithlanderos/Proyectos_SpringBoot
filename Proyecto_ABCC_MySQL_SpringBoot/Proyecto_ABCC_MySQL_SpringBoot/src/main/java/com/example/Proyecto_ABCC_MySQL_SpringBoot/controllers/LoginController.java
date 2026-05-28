package com.example.Proyecto_ABCC_MySQL_SpringBoot.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import org.springframework.ui.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class LoginController {
     @Value("${auth.service.url}")
    private String authServiceUrl;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpServletResponse response,
                                 Model model) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> body = Map.of(
                "username", username,
                "password", password
            );

            ResponseEntity<Map> resp = restTemplate.postForEntity(
                authServiceUrl + "/auth/login", body, Map.class
            );

            String token = (String) resp.getBody().get("token");

            // Guardar token en cookie
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(86400);
            response.addCookie(cookie);

            return "redirect:/";

        } catch (Exception e) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }
}
