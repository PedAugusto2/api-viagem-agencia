package com.agencia.viagem.agencia_viagem_api.controller;

import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.agencia.viagem.agencia_viagem_api.model.Role;
import com.agencia.viagem.agencia_viagem_api.model.User;
import com.agencia.viagem.agencia_viagem_api.repository.RoleRepository;
import com.agencia.viagem.agencia_viagem_api.repository.UserRepository;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


private final UserRepository userRepo;
private final RoleRepository roleRepo;
private final PasswordEncoder encoder;


public AuthController(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
this.userRepo = userRepo;
this.roleRepo = roleRepo;
this.encoder = encoder;
}


@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user) {
if (userRepo.findByUsername(user.getName()).isPresent()) {
return ResponseEntity.badRequest().body("Usuário já existe");
}


Role role = roleRepo.findByName("ROLE_USER").orElseGet(() -> roleRepo.save(new Role(null, "ROLE_USER")));


user.setPassword(encoder.encode(user.getPassword()));
user.setRoles(List.of(role));
userRepo.save(user);


return ResponseEntity.ok("Usuário registrado com sucesso!");
}
}