package com.example.Proyecto_ABCC_MySQL_SpringBoot.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.Proyecto_ABCC_MySQL_SpringBoot.utils.JwtUtil;
import jakarta.servlet.http.Cookie;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtFilter extends OncePerRequestFilter  {
     private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
                                    throws ServletException, IOException {

        String token = null;

        // Buscar token en cookies
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        if (token != null && jwtUtil.validarToken(token)) {
            String username = jwtUtil.extraerUsername(token);
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(username, null, List.of());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
