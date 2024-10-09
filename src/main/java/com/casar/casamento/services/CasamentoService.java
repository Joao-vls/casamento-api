package com.casar.casamento.services;

import com.casar.casamento.model.Casamento;
import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.model.Contratante;
import com.casar.casamento.repository.CasamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasamentoService {

    @Autowired
    private CasamentoRepository casamentoRepository;

    public Casamento save(Casamento casamento) {
        return casamentoRepository.save(casamento);
    }
    public boolean verificarSeCasamentoSemContratoJaEstaEmCasamento(CasamentoSemContrato casamentoSemContrato) {
        return casamentoRepository.existsByCasamentoSemContrato(casamentoSemContrato);
    }
}
