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

import com.triptodo.dto.VooRequestDTO;
import com.triptodo.dto.VooResponseDTO;
import com.triptodo.service.VooService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/voos")
public class VooController {

    private final VooService vooService;

    public VooController(VooService vooService) {
        this.vooService = vooService;
    }

    @PostMapping
    public ResponseEntity<VooResponseDTO> criar(@Valid @RequestBody VooRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vooService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VooResponseDTO>> listarTodos() {
        return ResponseEntity.ok(vooService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VooResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vooService.buscarPorId(id));
    }

    @GetMapping("/destino/{destinoId}")
    public ResponseEntity<List<VooResponseDTO>> listarPorDestino(@PathVariable Long destinoId) {
        return ResponseEntity.ok(vooService.listarPorDestino(destinoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VooResponseDTO> atualizar(@PathVariable Long id,
                                                    @Valid @RequestBody VooRequestDTO dto) {
        return ResponseEntity.ok(vooService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vooService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}