package com.example.auth_service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_service.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByUsername(String username);

}
