package com.casar.casamento.services;

import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.model.Usuario;
import com.casar.casamento.repository.CasamentoSemContratoRepository;
import com.casar.casamento.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasamentoSemContratoService {

    @Autowired
    private CasamentoSemContratoRepository casamentoSemContratoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<CasamentoSemContrato> getCasamentosPorUsuario(Usuario usuario) {
       Optional<Usuario> usuario1= usuarioRepository.findByEmail(usuario.getEmail());
        if (usuario1.isPresent()){
            return casamentoSemContratoRepository.findByUsuario(usuario);
        }else {
            return null;
        }
    }

    public CasamentoSemContrato createCasamentoSemContrato(@Valid CasamentoSemContrato casamentoSemContrato) {
         return casamentoSemContratoRepository.save(casamentoSemContrato);
    }
}
