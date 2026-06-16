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

import com.triptodo.dto.DestinoRequestDTO;
import com.triptodo.dto.DestinoResponseDTO;
import com.triptodo.service.DestinoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @PostMapping
    public ResponseEntity<DestinoResponseDTO> criar(@Valid @RequestBody DestinoRequestDTO dto) {
        DestinoResponseDTO destinoCriado = destinoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(destinoCriado);
    }

    @GetMapping
    public ResponseEntity<List<DestinoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(destinoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(destinoService.buscarPorId(id));
    }

    @GetMapping("/viagem/{viagemId}")
    public ResponseEntity<List<DestinoResponseDTO>> listarPorViagem(@PathVariable Long viagemId) {
        return ResponseEntity.ok(destinoService.listarPorViagem(viagemId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DestinoRequestDTO dto
    ) {
        return ResponseEntity.ok(destinoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        destinoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}