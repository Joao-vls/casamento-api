package com.casar.casamento.services;

import com.casar.casamento.repository.NoivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoivosService {
    @Autowired
    NoivosRepository noivosRepository;
    boolean isUsuario(int id){
        return noivosRepository.existsByUsuarioId(id);
    }

    public long count() {return noivosRepository.count();}
}
