package com.casar.casamento.repository;

import com.casar.casamento.model.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicosRepository extends JpaRepository<Servicos,Integer> {
    void deleteByCasamentoId(int casamentoId);
}
