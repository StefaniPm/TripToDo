package com.triptodo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triptodo.dto.ViagemRequestDTO;
import com.triptodo.dto.ViagemResponseDTO;
import com.triptodo.service.ViagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/viagens")
@CrossOrigin(origins = "http://localhost:5173")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @PostMapping
    public ResponseEntity<ViagemResponseDTO> criar(@Valid @RequestBody ViagemRequestDTO dto) {
        ViagemResponseDTO viagemCriada = viagemService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(viagemCriada);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ViagemResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(viagemService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> buscarPorId(
            @PathVariable Long id,
            @RequestParam Long usuarioId
    ) {
        return ResponseEntity.ok(viagemService.buscarPorId(id, usuarioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViagemResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestParam Long usuarioId,
            @Valid @RequestBody ViagemRequestDTO dto
    ) {
        return ResponseEntity.ok(viagemService.atualizar(id, usuarioId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id,
            @RequestParam Long usuarioId
    ) {
        viagemService.deletar(id, usuarioId);

        return ResponseEntity.noContent().build();
    }
}