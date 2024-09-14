package com.casar.casamento.repository;

import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasamentoSemContratoRepository  extends JpaRepository<CasamentoSemContrato, Integer> {
    List<CasamentoSemContrato> findByUsuario(Usuario usuario);
}
