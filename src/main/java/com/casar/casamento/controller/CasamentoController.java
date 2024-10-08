package com.casar.casamento.controller;

import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.services.CasamentoSemContratoService;
import com.casar.casamento.services.CasamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/casamento", produces = {"application/json"})
public class CasamentoController {
    @Autowired
    private CasamentoService casamentoService;

    @Autowired
    private CasamentoSemContratoService casamentoSemContratoService;

    @GetMapping("/verificar/{id}")
    public ResponseEntity<String> verificarCasamento(@PathVariable("id") int idCasamentoSemContrato) {
        Optional<CasamentoSemContrato> optionalCasamentoSemContrato = casamentoSemContratoService.getById(idCasamentoSemContrato);
        if (optionalCasamentoSemContrato.isPresent()) {
            CasamentoSemContrato casamentoSemContrato = optionalCasamentoSemContrato.get();
            boolean existe = casamentoService.verificarSeCasamentoSemContratoJaEstaEmCasamento(casamentoSemContrato);

            if (existe) {
                return new ResponseEntity<>("Casamento já existe para este CasamentoSemContrato.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Não há casamento relacionado a este CasamentoSemContrato.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("CasamentoSemContrato não encontrado.", HttpStatus.NOT_FOUND);
        }
    }
}