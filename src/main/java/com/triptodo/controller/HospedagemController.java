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

import com.triptodo.dto.HospedagemRequestDTO;
import com.triptodo.dto.HospedagemResponseDTO;
import com.triptodo.service.HospedagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hospedagens")
public class HospedagemController {

    private final HospedagemService hospedagemService;

    public HospedagemController(HospedagemService hospedagemService) {
        this.hospedagemService = hospedagemService;
    }

    @PostMapping
    public ResponseEntity<HospedagemResponseDTO> criar(@Valid @RequestBody HospedagemRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospedagemService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<HospedagemResponseDTO>> listarTodas() {
        return ResponseEntity.ok(hospedagemService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospedagemResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(hospedagemService.buscarPorId(id));
    }

    @GetMapping("/destino/{destinoId}")
    public ResponseEntity<List<HospedagemResponseDTO>> listarPorDestino(@PathVariable Long destinoId) {
        return ResponseEntity.ok(hospedagemService.listarPorDestino(destinoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospedagemResponseDTO> atualizar(@PathVariable Long id,
                                                           @Valid @RequestBody HospedagemRequestDTO dto) {
        return ResponseEntity.ok(hospedagemService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        hospedagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}