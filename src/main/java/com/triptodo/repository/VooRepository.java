package com.triptodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triptodo.model.Voo;

@Repository
public interface VooRepository extends JpaRepository<Voo, Long> {

    List<Voo> findByDestinoId(Long destinoId);
}