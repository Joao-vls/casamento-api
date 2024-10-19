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

        // Mantém apenas o findById que retorna Optional
        public Optional<Locais> findById(int id) {
            return locaisRepository.findById(id);
        }


}
