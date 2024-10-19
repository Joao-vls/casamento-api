package com.casar.casamento.repository;

import com.casar.casamento.model.Casamento;
import com.casar.casamento.model.CasamentoSemContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CasamentoRepository extends JpaRepository<Casamento, Integer> {
    boolean existsByCasamentoSemContrato(CasamentoSemContrato casamentoSemContrato);
}

