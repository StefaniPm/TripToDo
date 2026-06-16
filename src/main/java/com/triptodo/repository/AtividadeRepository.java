package com.triptodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triptodo.model.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findByDestinoId(Long destinoId);

    List<Atividade> findByDestinoIdOrderByDataAscHorarioAsc(Long destinoId);
}