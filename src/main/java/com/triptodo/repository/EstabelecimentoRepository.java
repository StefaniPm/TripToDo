package com.triptodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triptodo.model.Estabelecimento;
import com.triptodo.model.TipoEstabelecimento;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

    List<Estabelecimento> findByDestinoId(Long destinoId);

    List<Estabelecimento> findByTipo(TipoEstabelecimento tipo);

    List<Estabelecimento> findByDestinoIdAndTipo(Long destinoId, TipoEstabelecimento tipo);
}