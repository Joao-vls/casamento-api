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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/casamento", produces = {"application/json"})
public class CasamentoController {
    @Autowired
    private CasamentoService casamentoService;

    @Autowired
    private CasamentoSemContratoService casamentoSemContratoService;

    @GetMapping("/verificar/{id}")
    public ResponseEntity<Map<String, Object>> verificarCasamento(@PathVariable("id") int idCasamentoSemContrato) {
        Optional<CasamentoSemContrato> optionalCasamentoSemContrato = casamentoSemContratoService.getById(idCasamentoSemContrato);
        Map<String, Object> response = new HashMap<>();

        if (optionalCasamentoSemContrato.isPresent()) {
            CasamentoSemContrato casamentoSemContrato = optionalCasamentoSemContrato.get();
            boolean existe = casamentoService.verificarSeCasamentoSemContratoJaEstaEmCasamento(casamentoSemContrato);

            response.put("mensagem", existe ? "Casamento já existe para este CasamentoSemContrato." : "Não há casamento relacionado a este CasamentoSemContrato.");
            response.put("existe", existe);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("mensagem", "CasamentoSemContrato não encontrado.");
            response.put("existe", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}