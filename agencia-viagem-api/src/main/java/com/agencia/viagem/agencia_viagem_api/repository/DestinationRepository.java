package com.agencia.viagem.agencia_viagem_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agencia.viagem.agencia_viagem_api.model.Destino;
import java.util.List;

public interface DestinationRepository extends JpaRepository<Destino, Long> {
    List<Destino> findByNomeContainingIgnoreCase(String nome);
    List<Destino> findByLocalizacaoContainingIgnoreCase(String localizacao);
}