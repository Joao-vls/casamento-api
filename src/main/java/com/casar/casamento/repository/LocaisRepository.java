package com.casar.casamento.repository;

import com.casar.casamento.model.Locais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocaisRepository extends JpaRepository<Locais,Integer> {
}
