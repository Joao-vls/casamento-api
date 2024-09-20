package com.casar.casamento.services;

import com.casar.casamento.model.Locais;
import com.casar.casamento.repository.LocaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocaisService {

    @Autowired
    private LocaisRepository locaisRepository;

    public Locais getLocaisById(Integer id) {
        Optional<Locais> locais = locaisRepository.findById(id);
        return locais.orElse(null); // Retorna o Local ou null caso não exista
    }
}
