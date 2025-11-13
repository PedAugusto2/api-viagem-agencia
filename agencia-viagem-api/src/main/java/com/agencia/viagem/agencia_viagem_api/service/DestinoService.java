package com.agencia.viagem.agencia_viagem_api.service;


import com.agencia.viagem.agencia_viagem_api.model.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class DestinoService {
    
    private final List<Destino> destinos = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(1);
    
    
    public Destino cadastrarDestino(Destino destino) {
        destino.setId(contador.getAndIncrement());
        destino.setNumeroAvaliacoes(0);
        destino.setMediaAvaliacao(0.0);
        destinos.add(destino);
        return destino;
    }
    
    
    public List<Destino> listarTodosDestinos() {
        return new ArrayList<>(destinos);
    }
    
    
    public List<Destino> pesquisarDestinos(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return listarTodosDestinos();
        }
        
        String termoLowerCase = termo.toLowerCase();
        return destinos.stream()
                .filter(destino -> 
                    destino.getNome().toLowerCase().contains(termoLowerCase) ||
                    destino.getLocalizacao().toLowerCase().contains(termoLowerCase))
                .collect(Collectors.toList());
    }
    
    
    public Optional<Destino> buscarDestinoPorId(Long id) {
        return destinos.stream()
                .filter(destino -> destino.getId().equals(id))
                .findFirst();
    }
    
    
    public Optional<Destino> avaliarDestino(Long id, int nota) {
        if (nota < 1 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 10");
        }
        
        Optional<Destino> destinoOptional = buscarDestinoPorId(id);
        if (destinoOptional.isPresent()) {
            Destino destino = destinoOptional.get();
            
            
            double somaAtual = destino.getMediaAvaliacao() * destino.getNumeroAvaliacoes();
            int novoNumeroAvaliacoes = destino.getNumeroAvaliacoes() + 1;
            double novaMedia = (somaAtual + nota) / novoNumeroAvaliacoes;
            
            destino.setMediaAvaliacao(novaMedia);
            destino.setNumeroAvaliacoes(novoNumeroAvaliacoes);
            
            return Optional.of(destino);
        }
        
        return Optional.empty();
    }
    
    
    public Optional<Destino> atualizarDestino(Long id, Destino destinoAtualizado) {
        Optional<Destino> destinoExistente = buscarDestinoPorId(id);
        if (destinoExistente.isPresent()) {
            Destino destino = destinoExistente.get();
            
            destino.setNome(destinoAtualizado.getNome());
            destino.setLocalizacao(destinoAtualizado.getLocalizacao());
            destino.setDescricao(destinoAtualizado.getDescricao());
            destino.setPrecoMedio(destinoAtualizado.getPrecoMedio());
            
            return Optional.of(destino);
        }
        
        return Optional.empty();
    }
    
    
    public boolean excluirDestino(Long id) {
        return destinos.removeIf(destino -> destino.getId().equals(id));
    }
}