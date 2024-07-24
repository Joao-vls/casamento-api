package com.casar.casamento.repository;

import com.casar.casamento.model.Noivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoivosRepository extends JpaRepository<Noivos, Integer> {
    boolean existsByUsuarioId(int usuarioId);
}
