package com.casar.casamento.repository;

import com.casar.casamento.model.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratanteRepository extends JpaRepository<Contratante, Integer> {

    boolean existsByUsuarioId(int usuarioId);
}
