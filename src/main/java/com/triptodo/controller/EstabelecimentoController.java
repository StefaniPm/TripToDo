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
import org.springframework.web.bind.annotation.RestController;

import com.triptodo.dto.EstabelecimentoRequestDTO;
import com.triptodo.dto.EstabelecimentoResponseDTO;
import com.triptodo.model.TipoEstabelecimento;
import com.triptodo.service.EstabelecimentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estabelecimentos")
@CrossOrigin(origins = "http://localhost:5173")
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    public EstabelecimentoController(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoResponseDTO> criar(@Valid @RequestBody EstabelecimentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<EstabelecimentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(estabelecimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estabelecimentoService.buscarPorId(id));
    }

    @GetMapping("/destino/{destinoId}")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> listarPorDestino(@PathVariable Long destinoId) {
        return ResponseEntity.ok(estabelecimentoService.listarPorDestino(destinoId));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> listarPorTipo(@PathVariable TipoEstabelecimento tipo) {
        return ResponseEntity.ok(estabelecimentoService.listarPorTipo(tipo));
    }

    @GetMapping("/destino/{destinoId}/tipo/{tipo}")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> listarPorDestinoETipo(
            @PathVariable Long destinoId,
            @PathVariable TipoEstabelecimento tipo
    ) {
        return ResponseEntity.ok(estabelecimentoService.listarPorDestinoETipo(destinoId, tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstabelecimentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EstabelecimentoRequestDTO dto
    ) {
        return ResponseEntity.ok(estabelecimentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estabelecimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}