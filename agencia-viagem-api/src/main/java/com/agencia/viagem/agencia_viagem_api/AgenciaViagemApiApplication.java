package com.agencia.viagem.agencia_viagem_api;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.agencia.viagem.agencia_viagem_api.model.Role;
import com.agencia.viagem.agencia_viagem_api.model.User;
import com.agencia.viagem.agencia_viagem_api.repository.RoleRepository;
import com.agencia.viagem.agencia_viagem_api.repository.UserRepository;

@SpringBootApplication
public class AgenciaViagemApiApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(AgenciaViagemApiApplication.class, args);
	}
	@Bean
CommandLineRunner seed(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
return args -> {
var adminRole = roleRepo.findByName("ROLE_ADMIN")
.orElseGet(() -> roleRepo.save(new Role(null, "ROLE_ADMIN")));


var userRole = roleRepo.findByName("ROLE_USER")
.orElseGet(() -> roleRepo.save(new Role(null, "ROLE_USER")));


	if (userRepo.findByUsername("admin").isEmpty()) {
	var admin = new User();
	admin.setUsername("admin");
	admin.setPassword(encoder.encode("admin123"));
	admin.setRoles(List.of(adminRole));
	userRepo.save(admin);
	}


if (userRepo.findByUsername("user").isEmpty()) {
var user = new User();
user.setUsername("user");
user.setPassword(encoder.encode("user123"));
user.setRoles(List.of(userRole));
userRepo.save(user);
}
};
}
	

}
