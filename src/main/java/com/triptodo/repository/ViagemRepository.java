package com.triptodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triptodo.model.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
    
}