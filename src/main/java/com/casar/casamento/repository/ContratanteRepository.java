package com.casar.casamento.repository;

import com.casar.casamento.model.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratanteRepository extends JpaRepository<Contratante, Integer> {

    boolean existsByUsuarioId(int usuarioId);

    Optional<Contratante> findByUsuarioId(int usuarioId);
}
