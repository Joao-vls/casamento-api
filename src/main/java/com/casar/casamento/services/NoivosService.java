package com.casar.casamento.services;

import com.casar.casamento.model.Noivos;
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

    public Noivos getByUsuarioId(int usuarioId) {
        return noivosRepository.findByUsuarioId(usuarioId)
                .orElse(null);
    }

    public long count() {return noivosRepository.count();}
}
