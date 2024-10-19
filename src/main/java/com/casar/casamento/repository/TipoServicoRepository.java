package com.casar.casamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casar.casamento.model.TipoServico;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico,Integer>{

    
} 

