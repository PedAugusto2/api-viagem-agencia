package com.agencia.viagem.agencia_viagem_api.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password; // armazenar com BCrypt

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name="user_id"),
      inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;

    public Optional<User> getRoles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoles'");
    }

    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    public void setUsername(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUsername'");
    }

    public void setPassword(String encode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
    }

    public void setRoles(List<Role> of) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRoles'");
    }

    }