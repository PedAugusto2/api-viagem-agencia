package com.agencia.viagem.agencia_viagem_api.model;

import jakarta.persistence.*;

@Entity
    @Table(name = "roles")
    public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name; // ex: ROLE_USER, ROLE_ADMIN

    public Role() {

    }
    public Role(Long id, String name) { this.id = id; this.name = name; }

    }
