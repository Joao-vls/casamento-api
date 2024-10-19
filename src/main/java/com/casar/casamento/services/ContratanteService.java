package com.casar.casamento.services;


import com.casar.casamento.model.Contratante;
import com.casar.casamento.model.Noivos;
import com.casar.casamento.repository.ContratanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratanteService {
    @Autowired
    ContratanteRepository contratanteRepository;
    long count(){return contratanteRepository.count();}

    public Contratante getByUsuarioId(int usuarioId) {
        return contratanteRepository.findByUsuarioId(usuarioId)
                .orElse(null);
    }
    boolean isUsuario(int id){
       return contratanteRepository.existsByUsuarioId(id);
    }

    public Contratante save(Contratante contratante) {
        return contratanteRepository.save(contratante);
    }
}
