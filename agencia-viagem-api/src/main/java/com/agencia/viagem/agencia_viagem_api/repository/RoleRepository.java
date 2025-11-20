package com.agencia.viagem.agencia_viagem_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agencia.viagem.agencia_viagem_api.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

}

