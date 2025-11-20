package com.agencia.viagem.agencia_viagem_api.security;


import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.agencia.viagem.agencia_viagem_api.repository.UserRepository;


@Service
public class DatabaseUserDetailsService implements UserDetailsService {


private final UserRepository userRepository;


public DatabaseUserDetailsService(UserRepository userRepository) {
this.userRepository = userRepository;
}


@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
var user = userRepository.findByUsername(username)
.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));


var authorities = user.getRoles().stream()
.map(r -> new SimpleGrantedAuthority(r.getName()))
.toList();


return org.springframework.security.core.userdetails.User
.withUsername(user.getName())
.password(user.getPassword())
.authorities(authorities)
.build();
}
}