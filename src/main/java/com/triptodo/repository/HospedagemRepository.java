package com.triptodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triptodo.model.Hospedagem;

@Repository
public interface HospedagemRepository extends JpaRepository<Hospedagem, Long> {

    List<Hospedagem> findByDestinoId(Long destinoId);
}