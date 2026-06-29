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

import com.triptodo.dto.TransferRequestDTO;
import com.triptodo.dto.TransferResponseDTO;
import com.triptodo.service.TransferService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferResponseDTO> criar(@Valid @RequestBody TransferRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> listarTodos() {
        return ResponseEntity.ok(transferService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transferService.buscarPorId(id));
    }

    @GetMapping("/destino/{destinoId}")
    public ResponseEntity<List<TransferResponseDTO>> listarPorDestino(@PathVariable Long destinoId) {
        return ResponseEntity.ok(transferService.listarPorDestino(destinoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferResponseDTO> atualizar(@PathVariable Long id,
                                                         @Valid @RequestBody TransferRequestDTO dto) {
        return ResponseEntity.ok(transferService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transferService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}