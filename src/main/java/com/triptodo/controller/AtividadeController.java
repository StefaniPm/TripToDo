package com.triptodo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triptodo.dto.AtividadeRequestDTO;
import com.triptodo.dto.AtividadeResponseDTO;
import com.triptodo.service.AtividadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    private final AtividadeService atividadeService;

    public AtividadeController(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @PostMapping
    public ResponseEntity<AtividadeResponseDTO> criar(@Valid @RequestBody AtividadeRequestDTO dto) {
        AtividadeResponseDTO atividadeCriada = atividadeService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeCriada);
    }

    @GetMapping
    public ResponseEntity<List<AtividadeResponseDTO>> listarTodas() {
        return ResponseEntity.ok(atividadeService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atividadeService.buscarPorId(id));
    }

    @GetMapping("/destino/{destinoId}")
    public ResponseEntity<List<AtividadeResponseDTO>> listarPorDestino(@PathVariable Long destinoId) {
        return ResponseEntity.ok(atividadeService.listarPorDestino(destinoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtividadeRequestDTO dto
    ) {
        return ResponseEntity.ok(atividadeService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atividadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}