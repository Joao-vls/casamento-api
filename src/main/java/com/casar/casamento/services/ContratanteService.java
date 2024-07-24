package com.casar.casamento.services;


import com.casar.casamento.repository.ContratanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratanteService {
    @Autowired
    ContratanteRepository contratanteRepository;
    long count(){return contratanteRepository.count();}

    boolean isUsuario(int id){
       return contratanteRepository.existsByUsuarioId(id);
    }
}
