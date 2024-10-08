package com.casar.casamento.services;

import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.repository.CasamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasamentoService {

    @Autowired
    private CasamentoRepository casamentoRepository;

    public boolean verificarSeCasamentoSemContratoJaEstaEmCasamento(CasamentoSemContrato casamentoSemContrato) {
        return casamentoRepository.existsByCasamentoSemContrato(casamentoSemContrato);
    }
}
