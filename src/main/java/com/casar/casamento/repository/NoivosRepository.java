package com.casar.casamento.repository;

import com.casar.casamento.model.Noivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoivosRepository extends JpaRepository<Noivos, Integer> {
    boolean existsByUsuarioId(int usuarioId);



    Optional<Noivos> findByUsuarioId(int usuarioId);
}
