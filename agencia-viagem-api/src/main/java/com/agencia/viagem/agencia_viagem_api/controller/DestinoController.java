package com.agencia.viagem.agencia_viagem_api.controller;

import com.agencia.viagem.agencia_viagem_api.model.Destino;
import com.agencia.viagem.agencia_viagem_api.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/destinos")
@CrossOrigin(origins = "*")
public class DestinoController {
    
    @Autowired
    private DestinoService destinoService;
    
    
    @PostMapping
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
        try {
            Destino novoDestino = destinoService.cadastrarDestino(destino);
            return new ResponseEntity<>(novoDestino, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping
    public ResponseEntity<List<Destino>> listarTodosDestinos() {
        try {
            List<Destino> destinos = destinoService.listarTodosDestinos();
            return new ResponseEntity<>(destinos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/pesquisa")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam(required = false) String termo) {
        try {
            List<Destino> destinos = destinoService.pesquisarDestinos(termo);
            return new ResponseEntity<>(destinos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("http://localhost:8080/api/destinos")
    public ResponseEntity<Destino> visualizarDestino(@PathVariable Long id) {
        try {
            Optional<Destino> destino = destinoService.buscarDestinoPorId(id);
            return destino.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PatchMapping("http://localhost:8080/api/destinos")
    public ResponseEntity<Destino> avaliarDestino(@PathVariable Long id, @RequestBody Map<String, Integer> avaliacao) {
        try {
            Integer nota = avaliacao.get("nota");
            if (nota == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            Optional<Destino> destinoAtualizado = destinoService.avaliarDestino(id, nota);
            return destinoAtualizado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                                  .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PutMapping("http://localhost:8080/api/destinos")
    public ResponseEntity<Destino> atualizarDestino(@PathVariable Long id, @RequestBody Destino destino) {
        try {
            Optional<Destino> destinoAtualizado = destinoService.atualizarDestino(id, destino);
            return destinoAtualizado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                                  .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @DeleteMapping("http://localhost:8080/api/destinos")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        try {
            boolean removido = destinoService.excluirDestino(id);
            if (removido) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}