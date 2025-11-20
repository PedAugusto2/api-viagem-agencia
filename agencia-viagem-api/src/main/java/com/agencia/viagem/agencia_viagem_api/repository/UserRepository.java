package com.agencia.viagem.agencia_viagem_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.viagem.agencia_viagem_api.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}