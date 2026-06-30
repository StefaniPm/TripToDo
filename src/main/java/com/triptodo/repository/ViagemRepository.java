package com.triptodo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triptodo.model.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    List<Viagem> findByUsuarioId(Long usuarioId);

    Optional<Viagem> findByIdAndUsuarioId(Long id, Long usuarioId);
}